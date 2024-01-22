package com.robert.smartbi.demo.model.dto.chart;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class ChartCreateRequest implements Serializable {
    private String goal;
    private static final long serialVersionUID = 1L;
}
