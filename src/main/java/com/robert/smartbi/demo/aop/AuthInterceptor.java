package com.robert.smartbi.demo.aop;

import com.robert.smartbi.demo.annotation.Auth;
import com.robert.smartbi.demo.common.ErrorCode;
import com.robert.smartbi.demo.enums.UserRole;
import com.robert.smartbi.demo.exception.ThrowUtils;
import com.robert.smartbi.demo.model.vo.UserVO;
import com.robert.smartbi.demo.service.UserService;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Aspect
@Component
public class AuthInterceptor {
    @Resource
    private UserService userService;

    @Around("@annotation(auth)")
    public Object intercept(ProceedingJoinPoint joinPoint, Auth auth) throws Throwable {
        // value 为空时直接跳过
        String mustRole = auth.value();
        if (!StringUtils.hasText(mustRole))
            return joinPoint.proceed();
        UserRole requiredRole = UserRole.getRoleFromValue(mustRole);

        UserVO userVO = userService.getCurrentUser();
        String roleValue = userVO.getRole();
        UserRole currentUserRole = UserRole.getRoleFromValue(roleValue);


        ThrowUtils.throwIf(currentUserRole == null, ErrorCode.SYSTEM_ERROR,"用户角色不存在");
        ThrowUtils.throwIf(currentUserRole == UserRole.BAN,ErrorCode.NO_AUTH_ERROR,"用户被封禁");

        if (requiredRole == UserRole.ADMIN)
            ThrowUtils.throwIf(currentUserRole != UserRole.ADMIN,ErrorCode.NO_AUTH_ERROR,"需要管理员权限");

        return joinPoint.proceed();
    }
}
