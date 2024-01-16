package com.robert.smartbi.demo.manager;

import com.robert.smartbi.demo.common.ErrorCode;
import com.robert.smartbi.demo.exception.BusinessException;
import jakarta.annotation.Resource;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RedisLimiterManager {
    @Resource
    private RedissonClient redissonClient;

    public void doRateLimit(String key) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        // 每秒最多两次
        rateLimiter.trySetRate(RateType.OVERALL, 2, 1, RateIntervalUnit.SECONDS);
        boolean isApproved = rateLimiter.tryAcquire(1);
        if (!isApproved)
            throw new BusinessException(ErrorCode.TOO_MANY_REQUESTS_ERROR);
    }
}
