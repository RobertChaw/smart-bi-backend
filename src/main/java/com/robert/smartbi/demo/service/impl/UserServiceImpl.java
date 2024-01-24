package com.robert.smartbi.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robert.smartbi.demo.common.ErrorCode;
import com.robert.smartbi.demo.constant.UserConstant;
import com.robert.smartbi.demo.exception.ThrowUtils;
import com.robert.smartbi.demo.model.dto.user.UserLoginRequest;
import com.robert.smartbi.demo.model.dto.user.UserRegisterRequest;
import com.robert.smartbi.demo.model.dto.user.UserUpdateRequest;
import com.robert.smartbi.demo.model.entity.User;
import com.robert.smartbi.demo.mapper.UserMapper;
import com.robert.smartbi.demo.model.vo.UserVO;
import com.robert.smartbi.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final String SALT = "SALT";

    @Override
    public long register(UserRegisterRequest userRegisterRequest) {
        String userAccount = userRegisterRequest.getUserAccount();
        String password = userRegisterRequest.getPassword();
        // 检查用户名长度是否少于4位
        ThrowUtils.throwIf(userRegisterRequest.getUserAccount().length() < 4, ErrorCode.PARAMS_ERROR, "用户名长度不能少于4位");
        // 检查密码长度是否少于8位
        ThrowUtils.throwIf(userRegisterRequest.getPassword().length() < 8, ErrorCode.PARAMS_ERROR, "密码长度不能少于8位");
        
        synchronized (userAccount.intern()) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userAccount", userRegisterRequest.getUserAccount());
            User existingUser = baseMapper.selectOne(queryWrapper);
            ThrowUtils.throwIf(existingUser != null, ErrorCode.OPERATION_ERROR, "用户账号已存在");

            String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
            User user = new User();
            user.setUserAccount(userRegisterRequest.getUserAccount());
            user.setPassword(encryptedPassword);
            boolean savedResult = save(user);
            ThrowUtils.throwIf(!savedResult, ErrorCode.OPERATION_ERROR, "注册失败");
            return user.getId();
        }
    }

    public long updateUser(UserUpdateRequest userUpdateRequest) {
        String userAccount = userUpdateRequest.getUserAccount();
        String password = userUpdateRequest.getPassword();
        // 检查用户名长度是否少于4位
        if (userAccount != null)
            ThrowUtils.throwIf(userUpdateRequest.getUserAccount().length() < 4, ErrorCode.PARAMS_ERROR, "用户名长度不能少于4位");
        // 检查密码长度是否少于8位
        if (password != null)
            ThrowUtils.throwIf(userUpdateRequest.getPassword().length() < 8, ErrorCode.PARAMS_ERROR, "密码长度不能少于8位");

        synchronized (userAccount.intern()) {
            String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
            User user = new User();
            BeanUtils.copyProperties(userUpdateRequest, user);
            user.setPassword(encryptedPassword);
            boolean isSucceeded = updateById(user);
            ThrowUtils.throwIf(!isSucceeded, ErrorCode.OPERATION_ERROR);
            return user.getId();
        }
    }

    @Override
    public UserVO login(UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest) {
        String userAccount = userLoginRequest.getUserAccount();
        String password = userLoginRequest.getPassword();
        boolean isEmpty = !StringUtils.hasText(userAccount) || !StringUtils.hasText(password);
        ThrowUtils.throwIf(isEmpty, ErrorCode.PARAMS_ERROR, "用户名或密码不能为空");

        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("password", encryptedPassword);
        User user = getOne(queryWrapper);
        ThrowUtils.throwIf(user == null, ErrorCode.PARAMS_ERROR, "用户名或密码错误");

        httpServletRequest.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public UserVO getCurrentUser() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(UserConstant.USER_LOGIN_STATE);
        boolean isEmpty = session.getAttribute(UserConstant.USER_LOGIN_STATE) == null;
        ThrowUtils.throwIf(isEmpty, ErrorCode.NOT_LOGIN_ERROR, "未登录");

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public boolean logout() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpSession session = request.getSession();
        boolean isEmpty = session.getAttribute(UserConstant.USER_LOGIN_STATE) == null;
        ThrowUtils.throwIf(isEmpty, ErrorCode.NOT_LOGIN_ERROR, "未登录");

        session.removeAttribute(UserConstant.USER_LOGIN_STATE);
        return true;
    }


}
