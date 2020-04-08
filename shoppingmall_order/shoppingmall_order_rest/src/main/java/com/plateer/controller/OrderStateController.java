package com.plateer.controller;

import java.util.*;

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
public class OrderStateController {

	OrderServiceImpl orderService;
	OrderStateServiceImpl orderStateService;

	public OrderStateController(OrderStateServiceImpl orderStateService){

		this.orderStateService = orderStateService;
	}

	//빠져나갈 부분
	@GetMapping("{orderId}")
	public OrderDto getOrderFromOrderId(@PathVariable("orderId") String orderId){

		return orderService.getOrderDto(orderId);
	}

	@GetMapping("/list/{state}/{userid}")
	public List<OrderDto> getOrderStateList(@PathVariable("state") String state, @PathVariable("userid") String userid){

		return orderStateService.findOrderListFromUserid(userid, state);
	}

	//빠져나갈 부분
	@PostMapping("/order")
	public int order(@RequestBody OrderDto orderDto){

		return orderService.createOrder(orderDto);
	}

	@GetMapping("/{original}/{changed}/{orderid}")
	public boolean changeOrderState(@PathVariable("orderid") String orderid, @PathVariable("original") String original, @PathVariable String changed) {

		orderStateService.changeOrderState(orderid, original, changed);
		return true;
	}

	@GetMapping("/count/{state}/{userid}")
	public Map<String, Integer> getStateCountMap(@PathVariable String state, @PathVariable String userid){

		return orderStateService.getOrderStateCount(userid, state);
	}

	@GetMapping("/specificstatelist/{state}/{specific}/{userid}")
	public List<OrderDto> getSpecificStateList(@PathVariable String state, @PathVariable String specific, @PathVariable String userid){

		return orderStateService.getSpecificStatusOrderList(state, specific, userid);
	}
}
