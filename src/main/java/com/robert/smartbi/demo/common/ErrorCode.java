package com.robert.smartbi.demo.common;

public enum ErrorCode {

    SUCCESS(0, "OK"),

    PARAMS_ERROR(40000, "OK"),

    NOT_LOGIN_ERROR(40100, "未登录"),

    NO_AUTH_ERROR(40101, "无权限"),

    TOO_MANY_REQUESTS_ERROR(42900, "请求过多"),

    FORIBIDDEN_ERROR(40300, "禁止访问"),

    SYSTEM_ERROR(50000, "系统内部异常"),

    OPERATION_ERROR(50001, "操作失败");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
