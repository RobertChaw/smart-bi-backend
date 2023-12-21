package com.robert.smartbi.demo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName(value = "dataSource")
public class DataSource implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
    private String dbUrl;
    private String username;
    private String password;
    private String datasourceType;
    private Long userId;

    private Timestamp createTime;
    private Timestamp updateTime;

    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}