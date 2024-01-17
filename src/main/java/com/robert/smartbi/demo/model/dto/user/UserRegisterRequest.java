package com.robert.smartbi.demo.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 23123453456456L;
    private String userAccount;
    private String password;
}
