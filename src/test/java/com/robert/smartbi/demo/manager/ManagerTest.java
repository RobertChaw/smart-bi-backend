package com.robert.smartbi.demo.manager;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.model.PutObjectResult;
import com.robert.smartbi.demo.config.COSConfig;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class ManagerTest {
    @Resource
    private CosManager cosManager;
    @Resource
    private COSClient cosClient;

    @Test
    @SneakyThrows
    void testCosManager() {
        File file = new File("src/test/resources/msg.txt");
        PutObjectResult putObjectResult = cosManager.putObject("msg.txt", file);
        COSObject cosObject = cosManager.getObject("msg.txt");
        COSObjectInputStream cosObjectInputStream = cosObject.getObjectContent();
        byte[] bytes = cosObjectInputStream.readAllBytes();
        String result = new String(bytes);
        System.out.println("文件内容：" + result);
        Assert.isTrue(!result.isEmpty(), "上传失败");
    }
}
