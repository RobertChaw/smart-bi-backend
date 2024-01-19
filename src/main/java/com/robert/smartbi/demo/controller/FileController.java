package com.robert.smartbi.demo.controller;

import com.robert.smartbi.demo.annotation.Auth;
import com.robert.smartbi.demo.common.BaseResponse;
import com.robert.smartbi.demo.common.ResultUtils;
import com.robert.smartbi.demo.constant.FileConstant;
import com.robert.smartbi.demo.constant.UserConstant;
import com.robert.smartbi.demo.manager.COSManager;
import com.robert.smartbi.demo.model.vo.UserVO;
import com.robert.smartbi.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Resource
    private COSManager cosManager;
    @Resource
    private UserService userService;

    @PostMapping("/upload")
    @Auth(UserConstant.DEFAULT_ROLE)
    public BaseResponse<String> handleFileUpload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("type") String type) {
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
