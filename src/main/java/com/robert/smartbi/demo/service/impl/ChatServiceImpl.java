package com.robert.smartbi.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robert.smartbi.demo.model.entity.Chat;
import com.robert.smartbi.demo.mapper.ChatMapper;
import com.robert.smartbi.demo.model.entity.ChatWithChart;
import com.robert.smartbi.demo.service.ChatService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {
    @Resource
    ChatMapper chatMapper;

    @Override
    public List<ChatWithChart> listChatWithChartByUserId(IPage<ChatWithChart> page, Long userId) {
        return chatMapper.listChatWithChartByUserId(page, userId);
    }
}
