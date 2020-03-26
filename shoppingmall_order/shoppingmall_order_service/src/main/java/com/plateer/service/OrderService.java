package com.plateer.service;

import com.plateer.domain.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> findAllOrderFromUserId(String userid);
    OrderDto findOrderFromOrderId(String orderid);
}
