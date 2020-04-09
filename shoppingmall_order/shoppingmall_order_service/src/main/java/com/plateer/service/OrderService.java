package com.plateer.service;

import com.plateer.domain.OrderDto;

public interface OrderService {

    int createOrder(OrderDto orderDto);

    OrderDto getOrderDto(String orderId);

    OrderDto getFullOrder(String orderId);
}
