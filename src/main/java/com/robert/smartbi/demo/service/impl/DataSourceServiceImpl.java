package com.robert.smartbi.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robert.smartbi.demo.model.entity.DataSource;
import com.robert.smartbi.demo.mapper.DataSourceMapper;
import com.robert.smartbi.demo.service.DataSourceService;
import org.springframework.stereotype.Service;

@Service
public class DataSourceServiceImpl extends ServiceImpl<DataSourceMapper, DataSource> implements DataSourceService {

}
