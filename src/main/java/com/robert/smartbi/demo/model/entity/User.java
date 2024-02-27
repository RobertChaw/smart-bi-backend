package com.robert.smartbi.demo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName(value = "user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String userAccount;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String avatar;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String role;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Timestamp createTime;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Timestamp updateTime;

    @TableLogic
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}