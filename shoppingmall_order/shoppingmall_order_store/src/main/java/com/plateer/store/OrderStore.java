package com.plateer.store;

import com.plateer.domain.OrderDto;
import com.plateer.domain.orderstate.NormalOrderState;

import java.util.List;

public interface OrderStore {

    List<OrderDto> findAll(String userid);
    OrderDto retriveOne(String orderid);

    List<OrderDto> retriveNormalOrderList(String userid);
    List<OrderDto> retriveCancelOrderList(String userid);
    List<OrderDto> retriveExchangeOrderList(String userid);
    List<OrderDto> retriveReturnOrderList(String userid);
}
