package com.robert.smartbi.demo.enums;

import lombok.Getter;

public enum UserRole {

    USER("用户", "user"),
    ADMIN("管理员", "admin"),
    BAN("被封号", "ban");

    private final String text;
    private final String value;

    UserRole(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public static UserRole getRoleFromValue(String value) {
        for (UserRole role : UserRole.values())
            if (role.value.equals(value))
                return role;
        return null;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
