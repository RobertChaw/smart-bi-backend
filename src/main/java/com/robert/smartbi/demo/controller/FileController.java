package com.robert.smartbi.demo.controller;

import com.robert.smartbi.demo.annotation.Auth;
import com.robert.smartbi.demo.common.BaseResponse;
import com.robert.smartbi.demo.common.ResultUtils;
import com.robert.smartbi.demo.constant.FileConstant;
import com.robert.smartbi.demo.constant.UserConstant;
import com.robert.smartbi.demo.manager.COSManager;
import com.robert.smartbi.demo.model.dto.file.FileUploadRequest;
import com.robert.smartbi.demo.model.vo.UserVO;
import com.robert.smartbi.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
@Tag(name = "File")
public class FileController {
    @Resource
    private COSManager cosManager;
    @Resource
    private UserService userService;

    @PostMapping(path = "/upload")
    @Auth(UserConstant.DEFAULT_ROLE)
//    @Parameters({@Parameter(name = "file", in = ParameterIn.DEFAULT, required = true, schema = @Schema(name = "file", format = "binary"))})
    public BaseResponse<String> handleFileUpload(@RequestBody FileUploadRequest fileUploadRequest) {
        MultipartFile multipartFile = fileUploadRequest.getFile();
        String type = fileUploadRequest.getType();
        UserVO userVO = userService.getCurrentUser();
        String fileName = multipartFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String key = String.format("/%s/%s/%s", type, userVO.getId(), uuid + "-" + fileName);
        String url = FileConstant.COS_HOST + key;

        File file = null;
        try {
            file = File.createTempFile(key, null);
            multipartFile.transferTo(file);
            cosManager.putObject(key, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResultUtils.success(url);
    }
}
