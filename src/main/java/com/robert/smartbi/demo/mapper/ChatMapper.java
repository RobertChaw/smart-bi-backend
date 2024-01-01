package com.robert.smartbi.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.robert.smartbi.demo.model.vo.Chat;

import java.util.List;

public interface ChatMapper extends BaseMapper<com.robert.smartbi.demo.model.entity.Chat> {
    List<Chat> listChatWithChartByUserId(IPage<Chat> page, Long userId);
}
