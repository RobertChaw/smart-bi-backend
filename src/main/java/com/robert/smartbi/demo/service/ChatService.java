package com.robert.smartbi.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.robert.smartbi.demo.model.vo.Chat;

import java.util.List;

public interface ChatService extends IService<com.robert.smartbi.demo.model.entity.Chat> {
    List<Chat> listChatWithChartByUserId(IPage<Chat> page, Long userId);

}
