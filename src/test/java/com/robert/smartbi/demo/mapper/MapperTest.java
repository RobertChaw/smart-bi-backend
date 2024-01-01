package com.robert.smartbi.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.robert.smartbi.demo.model.vo.Chat;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperTest {
    @Resource
    private UserMapper userMapper;
    @Resource
    ChatMapper chatMapper;

    @Test
    void testSelect() {
        System.out.println("----- selectAll method test ------");
        QueryWrapper<com.robert.smartbi.demo.model.entity.Chat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", 1L);
        List<com.robert.smartbi.demo.model.entity.Chat> userList = chatMapper.selectList(queryWrapper);
        Assert.isTrue(1 < userList.size(), "成功");
        userList.forEach(System.out::println);
    }

    @Test
    void TestChatWithChart() {
        System.out.println("----- selectAll method test ------");
        Page<Chat> page = new Page<>(1, 10);
        List<Chat> chatList = chatMapper.listChatWithChartByUserId(page, 1L);
        Assert.isTrue(1 < chatList.size(), "成功");
        chatList.forEach(System.out::println);

    }
}
