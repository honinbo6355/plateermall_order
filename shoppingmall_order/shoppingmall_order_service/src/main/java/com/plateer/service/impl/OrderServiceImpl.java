package com.plateer.service.impl;

import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.NormalOrderState;
import com.plateer.domain.orderstate.OrderType;
import com.plateer.service.OrderService;
import com.plateer.store.OrderStore;
import com.plateer.store.mybatis.MyBatisOrderStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private MyBatisOrderStore orderStore;

    public OrderServiceImpl(MyBatisOrderStore orderStore){
        this.orderStore = orderStore;
    }

    @Override
    public List<OrderDto> findAllOrderFromUserId(String userid) {
        return orderStore.findAll(userid);
    }

    @Override
    public OrderDto findOrderFromOrderId(String orderid) {
        return orderStore.retriveOne(orderid);
    }

    @Override
    public List<OrderDto> findOrderListFromUserid(String userid, Enum<OrderType> typeEnum) {
        return orderStore.retriveOrderList(userid, typeEnum);
    }

    @Override
    public boolean createOrder(OrderDto orderDto) {
        orderDto.setOrderId(Integer.toString(orderStore.getNewOrderid()));
        OrderState normalOrderState = new NormalOrderState(orderDto.getOrderId(), orderDto.getOrderDate(), NormalOrderState.StatusType.SHIPPING.getStatus());
        orderStore.createOrder(orderDto, normalOrderState);
        return true;
    }
}
