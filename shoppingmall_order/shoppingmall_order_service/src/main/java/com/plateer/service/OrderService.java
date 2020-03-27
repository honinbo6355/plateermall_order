package com.plateer.service;

import com.plateer.domain.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> findAllOrderFromUserId(String userid);
    OrderDto findOrderFromOrderId(String orderid);
    List<OrderDto> findNormalOrderListFromUserid(String userid);
    List<OrderDto> findCancelOrderListFromUserid(String userid);
    List<OrderDto> findExchangeOrderListFromUserid(String userid);
    List<OrderDto> findReturnOrderListFromUserid(String userid);
}
