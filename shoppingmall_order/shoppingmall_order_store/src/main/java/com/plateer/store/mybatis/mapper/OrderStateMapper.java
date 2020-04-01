package com.plateer.store.mybatis.mapper;

import com.plateer.domain.OrderState;

import java.util.List;

public interface OrderStateMapper {

    OrderState getOrderStateFromOrderid(String orderid);

    int createNewOrderState(OrderState orderState);

    int deleteOrderState(String orderid);

    List<OrderState> getOrderStateListFromUserid(String userid);

    int countOrderState(String state);

}
