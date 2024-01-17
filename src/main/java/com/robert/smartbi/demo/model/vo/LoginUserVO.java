package com.robert.smartbi.demo.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class LoginUserVO implements Serializable {
    private Long id;

    private String username;
    private String userAccount;
    private String avatar;
    private String role;

    private Timestamp createTime;
    private Timestamp updateTime;
    private static final long serialVersionUID = 1L;
}
