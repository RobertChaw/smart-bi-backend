package com.robert.smartbi.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.robert.smartbi.demo.annotation.Auth;
import com.robert.smartbi.demo.bimq.BiMessageProducer;
import com.robert.smartbi.demo.common.BaseResponse;
import com.robert.smartbi.demo.common.ErrorCode;
import com.robert.smartbi.demo.common.FileUtils;
import com.robert.smartbi.demo.common.ResultUtils;
import com.robert.smartbi.demo.constant.CommonConstant;
import com.robert.smartbi.demo.constant.UserConstant;
import com.robert.smartbi.demo.exception.ThrowUtils;
import com.robert.smartbi.demo.mapper.ChartMapper;
import com.robert.smartbi.demo.model.dto.chart.ChartCreateRequest;
import com.robert.smartbi.demo.model.dto.chart.ChartListRequest;
import com.robert.smartbi.demo.model.dto.chart.ChartUpdateRequest;
import com.robert.smartbi.demo.model.entity.Chart;
import com.robert.smartbi.demo.model.vo.UserVO;
import com.robert.smartbi.demo.service.ChartService;
import com.robert.smartbi.demo.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/charts")
@Slf4j
@Tag(name = "Chart")
public class ChartController {

    @Resource
    private ChartService chartService;
    @Resource
    private UserService userService;
    @Resource
    private BiMessageProducer biMessageProducer;

    @GetMapping("/{id}")
    @Auth(UserConstant.USER_LOGIN_STATE)
    public BaseResponse<Chart> getChartById(@PathVariable Long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);

        UserVO userVO = userService.getCurrentUser();
        QueryWrapper<Chart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userVO.getId());
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
        UserVO userVO = userService.getCurrentUser();
        QueryWrapper<Chart> queryWrapper = new QueryWrapper<>();
        if (chartListRequest == null) return queryWrapper;
        Long id = chartListRequest.getId();
        Long userId = userVO.getId();
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

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Auth(UserConstant.USER_LOGIN_STATE)
    public BaseResponse<Long> createChart(@RequestBody ChartCreateRequest chartCreateRequest) {
        MultipartFile multipartFile = chartCreateRequest.getFile();
//        ThrowUtils.throwIf(chartCreateRequest == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(multipartFile == null, ErrorCode.PARAMS_ERROR);

        long size = multipartFile.getSize();
        ThrowUtils.throwIf(size > 1024 * 1024, ErrorCode.PARAMS_ERROR, "文件不能超过 1 MB");

        Chart chart = new Chart();
        UserVO userVO = userService.getCurrentUser();
        Long userId = userVO.getId();
        BeanUtils.copyProperties(chartCreateRequest, chart);
        chart.setUserId(userId);

        boolean isSucceeded = chartService.save(chart);
        ThrowUtils.throwIf(!isSucceeded, ErrorCode.OPERATION_ERROR);
        try {
            File file = File.createTempFile("smart-bi", null);
            multipartFile.transferTo(file);
            String data = FileUtils.transformExcel(file);
            ChartMapper chartBaseMapper = (ChartMapper) chartService.getBaseMapper();
            chartBaseMapper.createChartData(chart.getId());
            boolean isDataInsertSucceeded = chartBaseMapper.addChartData(chart.getId(), data);
            ThrowUtils.throwIf(!isDataInsertSucceeded, ErrorCode.OPERATION_ERROR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        biMessageProducer.sendMessage(Long.toString(chart.getId()));
        return ResultUtils.success(chart.getId());
    }

    @PutMapping("/{id}")
    @Auth(UserConstant.USER_LOGIN_STATE)
    public BaseResponse<Long> updateChart(@PathVariable Long id, @RequestBody ChartUpdateRequest chartUpdateRequest) {
        ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);
        UserVO userVO = userService.getCurrentUser();
        Long userId = userVO.getId();

        Chart chart = new Chart();
        BeanUtils.copyProperties(chartUpdateRequest, chart);

        chart.setId(id);
        chart.setUserId(userId);
        boolean isSucceeded = chartService.updateById(chart);
        ThrowUtils.throwIf(!isSucceeded, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(id);
    }

    @DeleteMapping("/{id}")
    @Auth(UserConstant.USER_LOGIN_STATE)
    public BaseResponse<Long> deleteChartById(@PathVariable Long id) {
        ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);

        UserVO userVO = userService.getCurrentUser();
        Long userId = userVO.getId();
        QueryWrapper<Chart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        queryWrapper.eq("id", id);
        boolean isSucceeded = chartService.remove(queryWrapper);
        ThrowUtils.throwIf(isSucceeded, ErrorCode.OPERATION_ERROR, "插入失败");
        return ResultUtils.success(id);
    }
}
