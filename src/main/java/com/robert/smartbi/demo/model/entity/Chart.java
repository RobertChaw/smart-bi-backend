package com.robert.smartbi.demo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName(value = "chart")
public class Chart implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;
    private String description;
    private Long datasourceId;
    private String chatHistory;
    private String sqlText;
    private String chartText;
    private Long chatId;

    private Timestamp createTime;
    private Timestamp updateTime;

    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}