package com.robert.smartbi.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChartData {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String data;
    private Timestamp createTime;
    private Timestamp updateTime;
}
