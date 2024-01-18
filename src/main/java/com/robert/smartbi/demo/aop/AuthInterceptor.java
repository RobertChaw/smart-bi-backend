package com.robert.smartbi.demo.aop;

import com.robert.smartbi.demo.annotation.Auth;
import com.robert.smartbi.demo.common.ErrorCode;
import com.robert.smartbi.demo.constant.UserConstant;
import com.robert.smartbi.demo.enums.UserRole;
import com.robert.smartbi.demo.exception.ThrowUtils;
import com.robert.smartbi.demo.model.entity.User;
import com.robert.smartbi.demo.model.vo.LoginUserVO;
import com.robert.smartbi.demo.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        LoginUserVO loginUserVO = userService.getCurrentUser(request);
        String roleValue = loginUserVO.getRole();
        UserRole currentUserRole = UserRole.getRoleFromValue(roleValue);


        ThrowUtils.throwIf(currentUserRole == null, ErrorCode.SYSTEM_ERROR,"用户角色不存在");
        ThrowUtils.throwIf(currentUserRole == UserRole.BAN,ErrorCode.NO_AUTH_ERROR,"用户被封禁");

        if (requiredRole == UserRole.ADMIN)
            ThrowUtils.throwIf(currentUserRole != UserRole.ADMIN,ErrorCode.NO_AUTH_ERROR,"需要管理员权限");

        return joinPoint.proceed();
    }
}
