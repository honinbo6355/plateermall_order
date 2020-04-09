package com.plateer.controller;

import java.util.*;

import com.plateer.service.OrderService;
import com.plateer.service.OrderStateService;
import com.plateer.service.impl.OrderServiceImpl;
import com.plateer.service.impl.OrderStateServiceImpl;
import org.springframework.web.bind.annotation.*;

import com.plateer.domain.OrderDto;

@CrossOrigin(allowCredentials = "true", origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT},
allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
        "Access-Control-Request-Headers", "Access-Control-Allow-Origin", "Set-Cookie", "Authorization"},
exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"}, maxAge = 3000)
@RestController
@RequestMapping("/api/order")
public class OrderController {

	OrderService orderService;

	public OrderController(OrderService orderService) {

		this.orderService = orderService;
	}

	@PostMapping("/order")
	public int order(@RequestBody OrderDto orderDto){
		System.out.println(orderDto);
		return orderService.createOrder(orderDto);
	}

	@GetMapping("{orderId}")
	public OrderDto getOrderFromOrderId(@PathVariable("orderId") String orderId){

		return orderService.getOrderDto(orderId);
	}

	@GetMapping("/full/{orderId}")
	public OrderDto getFullOrderInfoFromOrderId(@PathVariable("orderId") String orderId) {

		return orderService.getFullOrder(orderId);
	}

}
