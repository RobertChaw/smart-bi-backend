package com.robert.smartbi.demo.bimq;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BiMessageProducerTest {
    @Resource
    private BiMessageProducer biMessageProducer;

    @Test
    void runTest() {
        biMessageProducer.sendMessage(Long.toString(30L));
    }
}