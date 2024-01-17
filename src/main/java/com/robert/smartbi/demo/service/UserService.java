package com.robert.smartbi.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.robert.smartbi.demo.model.dto.user.UserLoginRequest;
import com.robert.smartbi.demo.model.dto.user.UserRegisterRequest;
import com.robert.smartbi.demo.model.entity.User;
import com.robert.smartbi.demo.model.vo.LoginUserVO;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {
    public long register(UserRegisterRequest userRegisterRequest);
    public LoginUserVO login(UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest);
}
