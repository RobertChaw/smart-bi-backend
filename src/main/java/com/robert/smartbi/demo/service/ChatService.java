package com.robert.smartbi.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.robert.smartbi.demo.model.entity.Chat;
import com.robert.smartbi.demo.model.entity.ChatWithChart;

import java.util.List;

public interface ChatService extends IService<Chat> {
    List<ChatWithChart> listChatWithChartByUserId(IPage<ChatWithChart> page, Long userId);

}
