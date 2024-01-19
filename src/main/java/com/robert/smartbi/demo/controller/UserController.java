package com.robert.smartbi.demo.controller;

import com.robert.smartbi.demo.annotation.Auth;
import com.robert.smartbi.demo.common.BaseResponse;
import com.robert.smartbi.demo.common.ErrorCode;
import com.robert.smartbi.demo.common.ResultUtils;
import com.robert.smartbi.demo.constant.UserConstant;
import com.robert.smartbi.demo.exception.BusinessException;
import com.robert.smartbi.demo.exception.ThrowUtils;
import com.robert.smartbi.demo.model.dto.user.UserLoginRequest;
import com.robert.smartbi.demo.model.dto.user.UserRegisterRequest;
import com.robert.smartbi.demo.model.entity.User;
import com.robert.smartbi.demo.model.vo.LoginUserVO;
import com.robert.smartbi.demo.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Resource
    private UserService userService;

/*    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.list();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateById(user);
    }*/

    @DeleteMapping("/{id}")
    @Auth(UserConstant.ADMIN_ROLE)
    public void deleteUserById(@PathVariable Long id) {
        userService.removeById(id);
    }

    @PostMapping("/login")
    public BaseResponse<LoginUserVO> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest) {
        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);
        LoginUserVO loginUserVO = userService.login(userLoginRequest, httpServletRequest);

        return ResultUtils.success(loginUserVO);
    }

    @PostMapping("/logout")
    public BaseResponse<Boolean> logout(HttpServletRequest servletRequest) {
        ThrowUtils.throwIf(servletRequest == null, ErrorCode.PARAMS_ERROR);
        boolean isSucceeded = userService.logout(servletRequest);
        return ResultUtils.success(isSucceeded);
    }

    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);
        long userId = userService.register(userRegisterRequest);
        return ResultUtils.success(userId);
    }

    @GetMapping("/getCurrentUser")
    public BaseResponse<LoginUserVO> getCurrentUser(HttpServletRequest servletRequest) {
        // 返回当前用户信息
        ThrowUtils.throwIf(servletRequest == null, ErrorCode.PARAMS_ERROR);
        LoginUserVO loginUserVO = userService.getCurrentUser();
        return ResultUtils.success(loginUserVO);
    }

    @PutMapping("/updateCurrentUser")
    public void updateCurrentUser(@RequestBody User updatedUser) {

    }
}
