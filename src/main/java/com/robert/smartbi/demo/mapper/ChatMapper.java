package com.robert.smartbi.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.robert.smartbi.demo.model.entity.Chat;
import com.robert.smartbi.demo.model.entity.ChatWithChart;

import java.util.List;

public interface ChatMapper extends BaseMapper<Chat> {
    List<ChatWithChart> listChatWithChartByUserId(IPage<ChatWithChart> page, Long userId);
}
