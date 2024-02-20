package com.robert.smartbi.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.robert.smartbi.demo.model.entity.Chart;
import com.robert.smartbi.demo.model.entity.ChartData;

public interface ChartMapper extends BaseMapper<Chart> {
    ChartData getChartData(long id);

    boolean addChartData(long id, String data);

    boolean createChartData(long id);


}
