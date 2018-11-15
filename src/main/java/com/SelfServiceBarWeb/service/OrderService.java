package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.mapper.OrderMapper;
import com.SelfServiceBarWeb.model.request.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Muki on 2018/11/15
 */
@Service
public class OrderService {
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public void createOrder(CreateOrderRequest createOrderRequest) throws Exception {
        orderMapper.createOrder(createOrderRequest);
    }
}
