package com.robert.smartbi.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.robert.smartbi.demo.annotation.Auth;
import com.robert.smartbi.demo.common.BaseResponse;
import com.robert.smartbi.demo.common.ErrorCode;
import com.robert.smartbi.demo.common.ResultUtils;
import com.robert.smartbi.demo.constant.UserConstant;
import com.robert.smartbi.demo.exception.ThrowUtils;
import com.robert.smartbi.demo.model.dto.user.UserListRequest;
import com.robert.smartbi.demo.model.dto.user.UserLoginRequest;
import com.robert.smartbi.demo.model.dto.user.UserRegisterRequest;
import com.robert.smartbi.demo.model.dto.user.UserUpdateRequest;
import com.robert.smartbi.demo.model.entity.User;
import com.robert.smartbi.demo.model.vo.UserVO;
import com.robert.smartbi.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User")
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
    @PutMapping("/{id}")
    public BaseResponse<Long> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        ThrowUtils.throwIf(userUpdateRequest == null, ErrorCode.PARAMS_ERROR);
        userUpdateRequest.setId(id);
        userService.updateUser(userUpdateRequest);
        return ResultUtils.success(id);
    }

    @GetMapping
    @Auth(UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<User>> getAllUsers(UserListRequest userListRequest) {
        long current = userListRequest.getCurrent();
        long pageSize = userListRequest.getPageSize();
        Page<User> page = userService.page(new Page<>(current, pageSize), getQueryWrapper(userListRequest));
        return ResultUtils.success(page);
    }

    private QueryWrapper<User> getQueryWrapper(UserListRequest userListRequest) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (userListRequest == null)
            return queryWrapper;
        Long id = userListRequest.getId();
        String username = userListRequest.getUsername();
        String userAccount = userListRequest.getUserAccount();
        String role = userListRequest.getRole();

        queryWrapper.eq(id != null, "id", id);
        queryWrapper.like(username != null, "username", username);
        queryWrapper.like(userAccount != null, "userAccount", userAccount);
        queryWrapper.eq(role != null, "role", role);
        queryWrapper.select(i -> !i.getColumn().equals("password"));

        return queryWrapper;
    }

    @DeleteMapping("/{id}")
    @Auth(UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> deleteUserById(@PathVariable Long id) {
        boolean isSucceeded = userService.removeById(id);
        ThrowUtils.throwIf(!isSucceeded, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(id);
    }

    @PostMapping("/login")
    public BaseResponse<UserVO> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest) {
        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);
        UserVO userVO = userService.login(userLoginRequest, httpServletRequest);

        return ResultUtils.success(userVO);
    }

    @PostMapping("/logout")
    public BaseResponse<Boolean> logout() {
        boolean isSucceeded = userService.logout();
        return ResultUtils.success(isSucceeded);
    }

    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);
        long userId = userService.register(userRegisterRequest);
        return ResultUtils.success(userId);
    }

    @GetMapping("/getCurrentUser")
    @Auth(UserConstant.DEFAULT_ROLE)
    public BaseResponse<UserVO> getCurrentUser() {
        // 返回当前用户信息
        UserVO userVO = userService.getCurrentUser();
        return ResultUtils.success(userVO);
    }

    @PutMapping("/updateCurrentUser")
    @Auth(UserConstant.DEFAULT_ROLE)
    public BaseResponse<Long> updateCurrentUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        UserVO userVO = userService.getCurrentUser();
        userUpdateRequest.setId(userVO.getId());
        userService.updateUser(userUpdateRequest);
        return ResultUtils.success(userVO.getId());
    }
}
