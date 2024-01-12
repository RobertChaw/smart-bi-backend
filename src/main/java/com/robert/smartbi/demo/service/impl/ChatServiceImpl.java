package com.robert.smartbi.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robert.smartbi.demo.mapper.ChatMapper;
import com.robert.smartbi.demo.service.ChatService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatServiceImpl extends ServiceImpl<ChatMapper, com.robert.smartbi.demo.model.entity.Chat> implements ChatService {
    @Resource
    ChatMapper chatMapper;

}
