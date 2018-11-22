package com.SelfServiceBarWeb.controller;


import com.SelfServiceBarWeb.model.request.CreateOrderRequest;
import com.SelfServiceBarWeb.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Muki on 2018/11/15
 */

@RequestMapping(path = "/orders")
@RestController
@EnableAutoConfiguration
@Api(tags = "Order", description = "订单相关操作")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "订单同步")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public void createOrder(@RequestBody CreateOrderRequest order) throws Exception {
        /*Map<String, List<String>> monitorRequest = new HashMap<>();
        List<String> seatIds = new ArrayList<>(Arrays.asList(order.getSeat_ids().split("\\+")));
        monitorRequest.put("seatId", seatIds);
        HttpResponseContent monitorResponse = CommonUtil.sendPost("http://10.108.122.210:5000/project", JSONObject.toJSONString(monitorRequest));
        System.out.println(monitorResponse.getContent());
        if (monitorResponse.getContent() != "0")
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.PLEASE_CLEAN);*/

        orderService.createOrder(order);
    }
}
