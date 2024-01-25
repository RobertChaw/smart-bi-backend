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
    private String goal;
    //    private String data;
    private String chartOption;
    private String status;
    private String reason;
    private String summary;
    private Timestamp createTime;
    private Timestamp updateTime;

    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}