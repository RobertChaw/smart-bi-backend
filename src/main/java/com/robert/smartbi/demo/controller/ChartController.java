package com.robert.smartbi.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.robert.smartbi.demo.annotation.Auth;
import com.robert.smartbi.demo.common.BaseResponse;
import com.robert.smartbi.demo.common.ErrorCode;
import com.robert.smartbi.demo.common.ResultUtils;
import com.robert.smartbi.demo.constant.CommonConstant;
import com.robert.smartbi.demo.constant.UserConstant;
import com.robert.smartbi.demo.exception.ThrowUtils;
import com.robert.smartbi.demo.model.dto.chart.ChartListRequest;
import com.robert.smartbi.demo.model.entity.Chart;
import com.robert.smartbi.demo.model.vo.LoginUserVO;
import com.robert.smartbi.demo.service.ChartService;
import com.robert.smartbi.demo.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/charts")
public class ChartController {

    @Resource
    private ChartService chartService;
    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    @Auth(UserConstant.USER_LOGIN_STATE)
    public BaseResponse<Chart> getChartById(@PathVariable Long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);

        LoginUserVO loginUserVO = userService.getCurrentUser();
        QueryWrapper<Chart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", loginUserVO.getId());
        queryWrapper.eq("id", id);
        Chart chart = chartService.getOne(queryWrapper);
        return ResultUtils.success(chart);
    }

    @GetMapping
    @Auth(UserConstant.USER_LOGIN_STATE)
    public BaseResponse<Page<Chart>> getAllCharts(ChartListRequest chartListRequest) {
        long current = chartListRequest.getCurrent();
        long pageSize = chartListRequest.getPageSize();
        QueryWrapper<Chart> queryWrapper = getQueryWrapper(chartListRequest);
        Page<Chart> chartPage = chartService.page(new Page<>(current, pageSize), queryWrapper);
        return ResultUtils.success(chartPage);
    }

    private QueryWrapper<Chart> getQueryWrapper(ChartListRequest chartListRequest) {
        LoginUserVO loginUserVO = userService.getCurrentUser();
        QueryWrapper<Chart> queryWrapper = new QueryWrapper<>();
        if (chartListRequest == null)
            return queryWrapper;
        Long id = chartListRequest.getId();
        Long userId = loginUserVO.getId();
        String goal = chartListRequest.getGoal();
        String status = chartListRequest.getStatus();
        String reason = chartListRequest.getReason();
        String summary = chartListRequest.getSummary();
        String sortField = chartListRequest.getSortField();
        String sortOrder = chartListRequest.getSortOrder();

        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(userId != null, "userId", userId);
        queryWrapper.like(goal != null, "goal", goal);
        queryWrapper.eq(status != null, "status", status);
        queryWrapper.like(reason != null, "reason", reason);
        queryWrapper.like(summary != null, "summary", summary);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(sortField != null, sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);

        return queryWrapper;
    }

    @PostMapping
    @Auth(UserConstant.USER_LOGIN_STATE)
    public BaseResponse<Long> createChart(@RequestBody Chart chart) {
        ThrowUtils.throwIf(chart == null, ErrorCode.PARAMS_ERROR);

        LoginUserVO loginUserVO = userService.getCurrentUser();
        Long userId = loginUserVO.getId();
        chart.setUserId(userId);
        boolean isSucceeded = chartService.save(chart);
        ThrowUtils.throwIf(!isSucceeded,ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(chart.getId());
    }

    @PutMapping("/{id}")
    @Auth(UserConstant.USER_LOGIN_STATE)
    public BaseResponse<Long> updateChart(@PathVariable Long id, @RequestBody Chart chart) {
        ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);

        LoginUserVO loginUserVO = userService.getCurrentUser();
        Long userId = loginUserVO.getId();
        chart.setId(id);
        chart.setUserId(userId);
        boolean isSucceeded = chartService.updateById(chart);
        ThrowUtils.throwIf(!isSucceeded,ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(chart.getId());
    }

    @DeleteMapping("/{id}")
    @Auth(UserConstant.USER_LOGIN_STATE)
    public BaseResponse<Long> deleteChartById(@PathVariable Long id) {
        ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);

        LoginUserVO loginUserVO = userService.getCurrentUser();
        Long userId = loginUserVO.getId();
        QueryWrapper<Chart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        queryWrapper.eq("id", id);
        boolean isSucceeded = chartService.remove(queryWrapper);
        ThrowUtils.throwIf(isSucceeded,ErrorCode.OPERATION_ERROR,"插入失败");
        return ResultUtils.success(id);
    }
}
