package com.plateer.store.mybatis.mapper;

import com.plateer.domain.OrderState;

public interface OrderStateMapper {

    OrderState getOrderStateFromOrderid(String orderid);
    int createNewOrderState(OrderState orderState);
    int deleteOrderState(String orderid);
}
