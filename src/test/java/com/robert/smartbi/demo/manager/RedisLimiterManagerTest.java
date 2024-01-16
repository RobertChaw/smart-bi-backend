package com.robert.smartbi.demo.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class RedisLimiterManagerTest {
    @Resource
    private RedisLimiterManager redisLimiterManager;

    @Test
    @SneakyThrows
    void runTest() {
        try {
            execute(2);
        } catch (Exception e) {
            Assert.isTrue(false, "限流测试失败");
        }
        log.info("正常速率测试通过");

        try {
            execute(3);
        } catch (Exception e) {
            log.info("超阈值速率测试通过");
            return;
        }
        Assert.isTrue(false, "限流测试失败");
    }

    void execute(int n) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<?>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int finalI = i;
            Future<?> future = executorService.submit(new Thread(new Runnable() {
                @Override
                public void run() {
                    redisLimiterManager.doRateLimit("test");
                    log.info("任务" + finalI + ":完成");
                }
            }));
            list.add(future);
        }

        for (int i = 0; i < n; i++) {
            list.get(i).get();
        }
    }
}