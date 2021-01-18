package com.llc.seckill.activity.service;

import org.springframework.stereotype.Service;

@Service
public class ActivityOrderService {

    public String PlaceOrder(long userId, long itemId) {
        // call validation service
        // if validation passed, create order
        // send to the queue
        return null;
    }
}
