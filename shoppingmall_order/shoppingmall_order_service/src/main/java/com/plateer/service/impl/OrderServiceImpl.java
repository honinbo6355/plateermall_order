package com.plateer.service.impl;

import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.NormalOrderState;
import com.plateer.domain.orderstate.OrderType;
import com.plateer.service.OrderService;
import com.plateer.store.OrderStore;
import com.plateer.store.mybatis.MyBatisOrderStore;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private MyBatisOrderStore orderStore;

    public OrderServiceImpl(MyBatisOrderStore orderStore){
        this.orderStore = orderStore;
    }

    @Override
    public List<OrderDto> findAllOrderFromUserId(String userid) {
        return orderStore.findAllOrderFromUserid(userid);
    }

    @Override
    public OrderDto findOrderFromOrderId(String orderid) {
        return orderStore.retriveOne(orderid);
    }

    @Override
    public List<OrderDto> findOrderListFromUserid(String userid, OrderType typeEnum) {

        return orderStore.findAllOrderFromUserid(userid).stream()
                .map(orderDto -> {
                    OrderState normalState = orderStore.retriveOrderStateFromOrderid(orderDto.getOrderId(), typeEnum);
                    orderDto.setOrderState(normalState);
                    return orderDto;
                })
                .filter(orderDto -> orderDto.getOrderState() != null)
                .collect(Collectors.toList());
    }

    @Override
    public int createOrder(OrderDto orderDto) {
        int newOrderId = orderStore.getNewOrderid();
        orderDto.setOrderId(Integer.toString(newOrderId));
        OrderState normalOrderState = new NormalOrderState(orderDto.getOrderId(), orderDto.getOrderDate(), OrderType.NORMAL.getDefaultStatus());
        orderStore.createOrder(orderDto, normalOrderState);
        return newOrderId;
    }

    @Override
    public boolean changeOrderState(String orderid, OrderType originalType, OrderType changedType) {
        OrderState orderState = orderStore.retriveOrderStateFromOrderid(orderid, originalType);
        orderState.setOrderState(changedType.getDefaultStatus());
        orderState.setStateChangeDate(getToday());
        orderStore.deleteOrderState(orderid, originalType);
        orderStore.createOrderState(orderState, changedType);
        return false;
    }

    private String getToday(){
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(today);
    }
}
