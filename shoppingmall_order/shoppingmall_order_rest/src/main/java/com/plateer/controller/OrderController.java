package com.plateer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.NormalOrderState;
@CrossOrigin(allowCredentials = "true", origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT},
allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
        "Access-Control-Request-Headers", "Access-Control-Allow-Origin", "Set-Cookie"},
exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"}, maxAge = 3000)
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@GetMapping("normalorderlist")
	public List<OrderDto> getOrderDto() {
		OrderState state = new NormalOrderState("1", "2020-03-24", "배송중");
		OrderDto orderDto1 = new OrderDto("orderid", "userid", "goodsid", "orderprice", state);
		OrderDto orderDto2 = new OrderDto("orderid", "userid", "goodsid", "orderprice", state);
		OrderDto orderDto3 = new OrderDto("orderid", "userid", "goodsid", "orderprice", state);
		List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
		orderDtoList.add(orderDto1);
		orderDtoList.add(orderDto2);
		orderDtoList.add(orderDto3);
		return orderDtoList;
	}
}
