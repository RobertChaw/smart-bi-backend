package com.robert.smartbi.demo.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 23123453456456L;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String userAccount;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
