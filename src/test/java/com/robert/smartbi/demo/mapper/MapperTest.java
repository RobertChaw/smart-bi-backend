package com.robert.smartbi.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.robert.smartbi.demo.model.entity.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    void testSelect() {
        System.out.println("----- selectAll method test ------");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 1L);
        List<User> userList = userMapper.selectList(queryWrapper);
        Assert.isTrue(!userList.isEmpty(), "用户不应该为空");
        userList.forEach(System.out::println);
    }

}
