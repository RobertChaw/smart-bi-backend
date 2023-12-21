package com.robert.smartbi.demo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName(value = "user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;
    private String userAccount;
    private String password;
    private String avatar;
    private String role;

    private Timestamp createTime;
    private Timestamp updateTime;

    @TableLogic
    private Boolean isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}