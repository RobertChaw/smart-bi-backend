package com.robert.smartbi.demo.model.dto.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@Schema
public class ChartUpdateRequest implements Serializable {
    private String goal;
    private String chartOption;
    private String status;
    private String reason;
    private String summary;
    private static final long serialVersionUID = 1L;
}
