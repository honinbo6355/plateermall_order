package com.plateer.service;

import com.plateer.domain.OrderDto;
import com.plateer.domain.orderstate.OrderType;

import java.util.List;

public interface OrderService {

    List<OrderDto> findAllOrderFromUserId(String userid);
    OrderDto findOrderFromOrderId(String orderid);
    List<OrderDto> findOrderListFromUserid(String userid, Enum<OrderType> typeEnum);
    boolean createOrder(OrderDto orderDto);
}
