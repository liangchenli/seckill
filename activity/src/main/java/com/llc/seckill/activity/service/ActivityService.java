package com.llc.seckill.activity.service;

import com.llc.seckill.activity.entity.Activity;
import com.llc.seckill.activity.entity.Commodity;
import com.llc.seckill.activity.repository.ActivityRepository;
import com.llc.seckill.activity.repository.CommodityRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class ActivityService {

    @Resource
    private ActivityRepository activityRepository;

    @Resource
    private CommodityRepository commodityRepository;

    public Activity addSeckillActivity(
            String name,
            long commodityId,
            BigDecimal seckillPrice,
            BigDecimal oldPrice,
            long seckillStock,
            String startTime,
            String endTime
    ) throws ParseException {
        startTime = startTime.substring(0, 10) + startTime.substring(11);
        endTime = endTime.substring(0, 10) + endTime.substring(11);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddhh:mm");
        Activity seckillActivity = new Activity();
        seckillActivity.setName(name);
        seckillActivity.setCommodityId(commodityId);
        seckillActivity.setSeckillPrice(seckillPrice);
        seckillActivity.setOldPrice(oldPrice);
        seckillActivity.setTotalStock(seckillStock);
        seckillActivity.setAvailableStock(seckillStock);
        seckillActivity.setLockStock(0L);
        seckillActivity.setActivityStatus(1);
        seckillActivity.setStartTime(format.parse(startTime));
        seckillActivity.setEndTime(format.parse(endTime));
        activityRepository.save(seckillActivity);
        return seckillActivity;
    }

    public List<Activity> getActiveActivities() {
        return activityRepository.listActiveActivities();
    }

    public Map<String, Object> GetActivityItemInfo(Map<String, Object> resultMap, long activityId) {
        Activity seckillActivity = activityRepository.getOne(activityId);
        Commodity seckillCommodity = commodityRepository.getOne(seckillActivity.getCommodityId());
        resultMap.put("seckillActivity", seckillActivity);
        resultMap.put("seckillCommodity", seckillCommodity);
        resultMap.put("seckillPrice", seckillActivity.getSeckillPrice());
        resultMap.put("oldPrice", seckillActivity.getOldPrice());
        resultMap.put("commodityId", seckillActivity.getCommodityId());
        resultMap.put("commodityName", seckillCommodity.getCommodityName());
        resultMap.put("commodityDesc", seckillCommodity.getCommodityDesc());
        return resultMap;
    }
}
