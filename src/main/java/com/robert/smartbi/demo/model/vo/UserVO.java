package com.robert.smartbi.demo.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Schema(name = "User", requiredMode = Schema.RequiredMode.REQUIRED)
public class UserVO implements Serializable {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)

    private String username;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String userAccount;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String avatar;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String role;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Timestamp createTime;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;
}
