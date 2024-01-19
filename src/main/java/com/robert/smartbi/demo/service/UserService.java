package com.robert.smartbi.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.robert.smartbi.demo.model.dto.user.UserLoginRequest;
import com.robert.smartbi.demo.model.dto.user.UserRegisterRequest;
import com.robert.smartbi.demo.model.dto.user.UserUpdateRequest;
import com.robert.smartbi.demo.model.entity.User;
import com.robert.smartbi.demo.model.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {
    long register(UserRegisterRequest userRegisterRequest);
    long updateUser(UserUpdateRequest userUpdateRequest);
    UserVO login(UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest);

    UserVO getCurrentUser();
    boolean logout();
}
