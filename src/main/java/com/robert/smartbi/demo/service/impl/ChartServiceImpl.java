package com.robert.smartbi.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robert.smartbi.demo.model.entity.Chart;
import com.robert.smartbi.demo.mapper.ChartMapper;
import com.robert.smartbi.demo.service.ChartService;
import org.springframework.stereotype.Service;

@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart> implements ChartService {

}
