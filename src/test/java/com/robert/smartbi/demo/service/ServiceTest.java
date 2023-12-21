package com.robert.smartbi.demo.service;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.robert.smartbi.demo.model.entity.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ServiceTest {
    @Resource
    UserService userService;

    @Test
    void testService() {
        System.out.println("----- testService method test ------");
        List<User> list = userService.list();
        Assert.isTrue(1 < list.size(), "成功");
        list.forEach(System.out::println);
    }
}
