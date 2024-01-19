package com.robert.smartbi.demo.model.dto.user;

import com.robert.smartbi.demo.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserListRequest extends PageRequest implements Serializable {
    private Long id;

    private String username;
    private String userAccount;
    private String role;
    private static final long serialVersionUID = 1L;
}
