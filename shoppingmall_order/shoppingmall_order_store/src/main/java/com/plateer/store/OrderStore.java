package com.plateer.store;

import com.plateer.domain.OrderDto;
import com.plateer.domain.orderstate.OrderType;

import java.util.List;

public interface OrderStore {

    List<OrderDto> findAll(String userid);
    OrderDto retriveOne(String orderid);

    List<OrderDto> retriveOrderList(String userid, Enum<OrderType> typeEnum);
}
