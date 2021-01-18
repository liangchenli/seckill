package com.llc.seckill.activity.controller;

import com.llc.seckill.activity.entity.Activity;
import com.llc.seckill.activity.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @RequestMapping("/addSeckillActivity")
    public String addSeckillActivity() {
        System.out.println("Hit");
        return "add_activity";
    }

    @RequestMapping("/addSeckillActivityAction")
    public String addSeckillActivityAction(
            @RequestParam("name") String name,
            @RequestParam("commodityId") long commodityId,
            @RequestParam("seckillPrice") BigDecimal seckillPrice,
            @RequestParam("oldPrice") BigDecimal oldPrice,
            @RequestParam("seckillStock") long seckillStock,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            Map<String, Object> resultMap
    ) throws ParseException {
        Activity seckillActivity = activityService.addSeckillActivity(name, commodityId, seckillPrice, oldPrice, seckillStock, startTime, endTime);
        resultMap.put("seckillActivity", seckillActivity);
        return "add_success";
    }

    // Use case: list all the active activities
    @RequestMapping("/seckills")
    public String activityList(Map<String, Object> resultMap) {
        // Get a list of active activities
        List<Activity> seckillActivities = activityService.getActiveActivities();
        resultMap.put("seckillActivities", seckillActivities);
        return "seckill_activity";
    }

    // Use case: select one activity page and see commodity
    @RequestMapping("/item/{seckillActivityId}")
    public String getItem(Map<String, Object> resultMap, @PathVariable long seckillActivityId) {
        resultMap = activityService.GetActivityItemInfo(resultMap, seckillActivityId);
        return "seckill_item";
    }
}
