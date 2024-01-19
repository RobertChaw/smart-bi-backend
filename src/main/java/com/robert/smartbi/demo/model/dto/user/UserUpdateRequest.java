package com.robert.smartbi.demo.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateRequest implements Serializable {
    private Long id;

    private String username;
    private String userAccount;
    private String password;
    private String avatar;
    private String role;
    private static final long serialVersionUID = 1L;
}
