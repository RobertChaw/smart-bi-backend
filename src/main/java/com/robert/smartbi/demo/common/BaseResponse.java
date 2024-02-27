package com.robert.smartbi.demo.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema
public class BaseResponse<T> implements Serializable {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private int code;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private T data;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
