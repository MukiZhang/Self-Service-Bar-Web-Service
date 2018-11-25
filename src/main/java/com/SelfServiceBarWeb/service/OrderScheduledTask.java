package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Created by Muki on 2018/11/22
 */
@Component
public class OrderScheduledTask {
    @Autowired
    public OrderMapper orderMapper;

    //每半个小时扫描一次
    @Scheduled(cron = "0 0/30 * * * *")
    public void cron() throws Exception {

    }
}
