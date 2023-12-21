package com.robert.smartbi.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robert.smartbi.demo.model.entity.Chat;
import com.robert.smartbi.demo.mapper.ChatMapper;
import com.robert.smartbi.demo.service.ChatService;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {

}
