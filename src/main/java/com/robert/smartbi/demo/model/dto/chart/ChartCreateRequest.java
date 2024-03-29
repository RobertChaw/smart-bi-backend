package com.robert.smartbi.demo.model.dto.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@Schema
public class ChartCreateRequest implements Serializable {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String goal;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private MultipartFile file;

    private static final long serialVersionUID = 1L;
}
