package com.robert.smartbi.demo.model.dto.file;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@Schema
public class FileUploadRequest implements Serializable {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private MultipartFile file;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;
    private static final long serialVersionUID = 1L;
}
