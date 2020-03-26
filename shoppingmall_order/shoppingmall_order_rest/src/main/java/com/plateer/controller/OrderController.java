package com.plateer.controller;

import java.util.ArrayList;
import java.util.List;

import com.plateer.domain.orderstate.CancelOrderState;
import com.plateer.domain.orderstate.ExchangeOrderState;
import com.plateer.domain.orderstate.ReturnOrderState;
import com.plateer.service.impl.OrderServiceImpl;
import org.springframework.web.bind.annotation.*;

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

	OrderServiceImpl orderService;

	public OrderController(OrderServiceImpl orderService){
		this.orderService = orderService;
	}

	@GetMapping("normalorderlist")
	public List<OrderDto> getOrderDtoList() {
		OrderState state = new NormalOrderState("1", "2020-03-24", "배송중");
		OrderDto orderDto1 = new OrderDto("orderid1", "userid", "1203917700",1 ,"orderprice", "2020-04-03","사이즈 : 235",state);
		OrderDto orderDto2 = new OrderDto("orderid2", "userid", "1203917700", 1,"orderprice", "2020-04-03","사이즈 : 235",state);
		OrderDto orderDto3 = new OrderDto("orderid3", "userid", "1203917700", 1,"orderprice", "2020-04-03","사이즈 : 235",state);
		List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
		orderDtoList.add(orderDto1);
		orderDtoList.add(orderDto2);
		orderDtoList.add(orderDto3);
		return orderDtoList;
	}

	//orderid로 하나 가져오는거
	@GetMapping("order/{orderId}")
	public OrderDto getOrderDto(){
		return new OrderDto("1", "testid", "1203917700", 1, "testOrderPrice", "2020-04-03","사이즈 : 235",new NormalOrderState("1", "2020-04-25", "배송중"));
	}

	@GetMapping("list/{state}/{userid}")
	public List<OrderDto> getOrderStateList(@PathVariable("state") String state, @PathVariable("userid") String userid){
		//map으로 생성자 저장하는것. 충분히 할만하다.
		List<OrderDto> orderList = new ArrayList<>();
		if(state.equals("cancel")){
			OrderState cancelOrderState = new CancelOrderState("1", "2020-03-02", "취소요청");
			OrderDto orderDto1 = new OrderDto("orderid1", "userid", "1203917700",1 ,"orderprice", "2020-04-03","사이즈 : 235", cancelOrderState);
			orderList.add(orderDto1);
		}
		else if(state.equals("exchange")){
			OrderState exchangeOrderState = new ExchangeOrderState("1", "2020-05-03", "교환진행중");
			OrderDto orderDto1 = new OrderDto("orderid1", "userid", "1203917700",1 ,"orderprice", "2020-04-03","사이즈 : 235", exchangeOrderState);
			orderList.add(orderDto1);
		}
		else if(state.equals("return")){
			OrderState returnOrderState = new ReturnOrderState("1", "2020-05-03", "반품진행중");
			OrderDto orderDto1 = new OrderDto("orderid1", "userid", "1203917700",1 ,"orderprice", "2020-04-03","사이즈 : 235", returnOrderState);
			orderList.add(orderDto1);
		}
		else if(state.equals("normal")){
			OrderState normalOrderState = new NormalOrderState("202000002", "2020-03-26", "배송중");
			OrderDto orderDto1 = new OrderDto("202000002", "testid", "1203973748",1 ,"55000", "2020-03-25","사이즈 : 235", normalOrderState);
			orderList.add(orderDto1);
		}

		return orderList;

	}

	@GetMapping("test")
	public List<OrderDto> test(){
		return orderService.findAllOrderFromUserId("testid");
	}
}
