package com.plateer.store;

import com.plateer.domain.OrderDto;

import java.util.List;

public interface OrderStore {

    List<OrderDto> findAll(String userid);
    OrderDto retriveOne(String orderid);
}
