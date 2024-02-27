package com.robert.smartbi.demo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName(value = "chart")
public class Chart implements Serializable {
    @TableId(type = IdType.AUTO)
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String goal;
    //    private String data;
//    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String chartOption;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String reason;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String summary;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Timestamp createTime;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Timestamp updateTime;

    @TableLogic
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}