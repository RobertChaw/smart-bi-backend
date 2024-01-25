package com.robert.smartbi.demo.mapper;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.robert.smartbi.demo.model.entity.ChartData;
import jakarta.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ChartMapperTest {
    @Resource
    private ChartMapper chartMapper;
    @Test
   void runTest(){
//        chartMapper.createChartData(456);
        chartMapper.addChartData(456,"Test");
        ChartData chartData = chartMapper.getChartData(456);
        String data =chartData.getData();
        Assert.isTrue("Test".equals(data),"测试失败");
    }

}