package com.llc.seckill.activity.controller;

import com.llc.seckill.activity.service.ActivityOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class ActivityOrderController {

    @Resource
    private ActivityOrderService activityOrderService;

    @RequestMapping("/seckill/buy/{userId}}/{itemId}")
    public ModelAndView PlaceOrder(@PathVariable long userId, @PathVariable long itemId) {

        ModelAndView modelAndView = new ModelAndView();
        try {
            String orderNo = activityOrderService.PlaceOrder(userId, itemId);
            if (orderNo != null) {
                modelAndView.addObject("resultInfo", "Succeeded，order creating, order No is ：" + orderNo);
                modelAndView.addObject("orderNo", orderNo);
            } else {
                modelAndView.addObject("resultInfo", "Sorry, item is out of stock");
            }
        } catch (Exception e) {
            System.out.println("System Error" + e.toString());
            modelAndView.addObject("resultInfo", "Sorry, item is out of stock");
        }
        modelAndView.setViewName("seckill_result");
        return modelAndView;
    }
}
