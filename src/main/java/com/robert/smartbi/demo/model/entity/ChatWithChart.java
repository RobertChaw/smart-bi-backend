package com.robert.smartbi.demo.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ChatWithChart implements Serializable {
    private Long id;
    private String name;
    private Long userId;
    private Date createTime;
    private Date updateTime;

    private List<Chart> chartList;

    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

