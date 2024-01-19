package com.robert.smartbi.demo.model.dto.chart;

import com.robert.smartbi.demo.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChartListRequest extends PageRequest implements Serializable {
    private Long id;

//    private Long userId;
    private String goal;
    //    private String data;
//    private String chartOption;
    private String status;
    private String reason;
    private String summary;
    private String createTime;
    private String updateTime;
    private static final long serialVersionUID = 1L;
}
