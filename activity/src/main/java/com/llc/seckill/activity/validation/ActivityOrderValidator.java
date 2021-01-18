package com.llc.seckill.activity.validation;

import com.llc.seckill.redis.RedisService;

import javax.annotation.Resource;

public class ActivityOrderValidator implements Validator
{
    @Resource
    private RedisService redisService;

    @Override
    public boolean validate(Long userId, Long itemId) {
        // check if the item is out of stock
        // 1. check redis
        // 2. if redis return and in stock, return true
        // 3. if redis return but not in stock, return false
        // 4. if redis no value returned, check db, then set value (even db miss)
        return false;
    }
}
