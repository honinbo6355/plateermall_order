package com.plateer.controller;

import java.util.*;
import java.util.function.Supplier;

import com.plateer.domain.orderstate.*;
import com.plateer.service.impl.OrderServiceImpl;
import org.springframework.web.bind.annotation.*;

import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;

@CrossOrigin(allowCredentials = "true", origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT},
allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
        "Access-Control-Request-Headers", "Access-Control-Allow-Origin", "Set-Cookie"},
exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"}, maxAge = 3000)
@RestController
@RequestMapping("/api/order")
public class OrderController {

	OrderServiceImpl orderService;

	public OrderController(OrderServiceImpl orderService){

		this.orderService = orderService;
	}

	@GetMapping("{orderId}")
	public OrderDto getOrderFromOrderId(@PathVariable("orderId") String orderId){
		return orderService.findOrderFromOrderId(orderId);
	}

	@GetMapping("/list/{state}/{userid}")
	public List<OrderDto> getOrderStateList(@PathVariable("state") String state, @PathVariable("userid") String userid){
		Enum<OrderType> requestOrderType = OrderType.valueOf(state.toUpperCase());
		return orderService.findOrderListFromUserid(userid, requestOrderType);
	}

}
