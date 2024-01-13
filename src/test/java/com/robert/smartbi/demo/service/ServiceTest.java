package com.robert.smartbi.demo.service;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.robert.smartbi.demo.mapper.ChartMapper;
import com.robert.smartbi.demo.model.entity.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ServiceTest {
    @Resource
    UserService userService;
    @Resource
    ChartMapper chartMapper;

    //
    @Test
    void testService() {
        System.out.println("----- testService method test ------");
        List<User> list = userService.list();
        list.forEach(System.out::println);
        Assert.isTrue(1 < list.size(), "user 列表不能为空");
    }
}
