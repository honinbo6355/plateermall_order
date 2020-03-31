package com.plateer.store;

import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.OrderType;

import java.util.List;

public interface OrderStore {

    List<OrderDto> findAllOrderFromUserid(String userid);
    OrderDto retriveOne(String orderid);

//    List<OrderDto> retriveOrderList(String userid, Enum<OrderType> typeEnum);
    OrderState retriveOrderStateFromOrderid(String orderid, OrderType typeEnum);
    void createOrder(OrderDto orderDto, OrderState orderState);
    int getNewOrderid();
    boolean createOrderState(OrderState orderState, OrderType typeEnum);
    boolean deleteOrderState(String orderid, OrderType typeEnum);
    List<OrderState> findOrderStateListFromUserid(String userid, OrderType typeEnum);
}
