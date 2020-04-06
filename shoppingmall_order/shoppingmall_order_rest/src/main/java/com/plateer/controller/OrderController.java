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
        "Access-Control-Request-Headers", "Access-Control-Allow-Origin", "Set-Cookie", "Authorization"},
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
		return orderService.findOrderListFromUserid(userid, state);
	}

	@PostMapping("/order")
	public int order(@RequestBody OrderDto orderDto){
		return orderService.createOrder(orderDto);
	}

	@GetMapping("/{original}/{changed}/{orderid}")
	public boolean changeOrderState(@PathVariable("orderid") String orderid, @PathVariable("original") String original, @PathVariable String changed) {
		orderService.changeOrderState(orderid, original, changed);
		return true;
	}

	@GetMapping("/count/{state}/{userid}")
	public Map<String, Integer> getStateCountMap(@PathVariable String state, @PathVariable String userid){
		return orderService.getOrderStateCount(userid, state);
	}

	@GetMapping("/specificstatelist/{state}/{specific}/{userid}")
	public List<OrderDto> getSpecificStateList(@PathVariable String state, @PathVariable String specific, @PathVariable String userid){
		return orderService.getSpecificStatusOrderList(state, specific, userid);
	}
}
