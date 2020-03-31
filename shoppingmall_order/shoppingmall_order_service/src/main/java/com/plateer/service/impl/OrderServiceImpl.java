package com.plateer.service.impl;

import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.*;
import com.plateer.service.OrderService;
import com.plateer.store.mybatis.MyBatisOrderStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private MyBatisOrderStore orderStore;
    private Map<OrderType, Supplier<OrderState>> orderStateMap;

    public OrderServiceImpl(MyBatisOrderStore orderStore){

        this.orderStore = orderStore;
        this.orderStateMap = new HashMap<>();
        this.orderStateMap.put(OrderType.NORMAL, NormalOrderState::new);
        this.orderStateMap.put(OrderType.CANCEL, CancelOrderState::new);
        this.orderStateMap.put(OrderType.EXCHANGE, ExchangeOrderState::new);
        this.orderStateMap.put(OrderType.RETURN, ReturnOrderState::new);
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

        List<OrderDto> orderList = orderStore.findAllOrderFromUserid(userid);
        List<OrderState> stateList = orderStore.findOrderStateListFromUserid(userid, typeEnum);
        return stateList.stream()
                .flatMap(orderState -> orderList.stream()
                        .filter(orderDto -> orderDto.getOrderId().equals(orderState.getOrderId()))
                        .map(orderDto -> {
                            orderDto.setOrderState(orderState);
                            return orderDto;
                        }))
                .collect(Collectors.toList());

//        return orderStore.findAllOrderFromUserid(userid).stream()
//                .map(orderDto -> {
//                    OrderState normalState = orderStore.retriveOrderStateFromOrderid(orderDto.getOrderId(), typeEnum);
//                    orderDto.setOrderState(normalState);
//                    return orderDto;
//                })
//                .filter(orderDto -> orderDto.getOrderState() != null)
//                .collect(Collectors.toList());
    }

    @Override
    public int createOrder(OrderDto orderDto) {
        int newOrderId = orderStore.getNewOrderid();
        orderDto.setOrderId(Integer.toString(newOrderId));
        OrderState normalOrderState = new NormalOrderState(orderDto.getOrderId(), orderDto.getOrderDate(), OrderType.NORMAL.getDefaultStatus(), orderDto.getUserId());
        orderStore.createOrder(orderDto, normalOrderState);
        return newOrderId;
    }

    @Override
    public boolean changeOrderState(String orderid, OrderType originalType, OrderType changedType) {
        OrderState orderState = orderStore.retriveOrderStateFromOrderid(orderid, originalType);
        OrderState changedState = orderStateMap.get(changedType).get();
        changedState.setOrderId(orderState.getOrderId());
        changedState.setOrderState(changedType.getDefaultStatus());
        changedState.setStateChangeDate(getToday());
        changedState.setUserId(orderState.getUserId());
        orderStore.deleteOrderState(orderid, originalType);
        orderStore.createOrderState(orderState, changedType);
        return true;
    }

    private String getToday(){
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(today);
    }
}
