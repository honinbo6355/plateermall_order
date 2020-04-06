package com.plateer.store;

import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.OrderType;

import java.util.List;

public interface OrderStateStore {

    OrderState retriveOrderStateFromOrderid(String orderid, OrderType typeEnum);

    void createOrderState(OrderState orderState, OrderType typeEnum);

    boolean deleteOrderState(String orderid, OrderType typeEnum);

    List<OrderState> findOrderStateListFromUserid(String userid, OrderType typeEnum);

    int getStateCountFromUserid(String userid, String state, OrderType typeEnum);

    List<OrderState> findSpecificOrderStateListFromUserid(String userid, String state, OrderType typeEnum);
}
