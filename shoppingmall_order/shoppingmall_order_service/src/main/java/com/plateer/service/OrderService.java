package com.plateer.service;

import com.plateer.domain.OrderDto;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderDto findOrderFromOrderId(String orderid);

    List<OrderDto> findOrderListFromUserid(String userid, String orderType);

    int createOrder(OrderDto orderDto);

    boolean changeOrderState(String orderid, String original, String changed);

    Map<String, Integer> getOrderStateCount(String userid, String type);

    List<OrderDto> getSpecificStatusOrderList(String state, String specific, String userid);
}
