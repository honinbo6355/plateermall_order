package com.plateer.service.impl;

import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;
import com.plateer.domain.StatusTypeEnum;
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
    public List<OrderDto> findOrderListFromUserid(String userid, String orderType) {
        OrderType requestOrderType = OrderType.valueOf(orderType.toUpperCase());
        List<OrderDto> orderList = orderStore.findAllOrderFromUserid(userid);
        List<OrderState> stateList = orderStore.findOrderStateListFromUserid(userid, requestOrderType);
        return stateList.stream()
                .flatMap(orderState -> orderList.stream()
                        .filter(orderDto -> orderDto.getOrderId().equals(orderState.getOrderId()))
                        .map(orderDto -> {
                            orderDto.setOrderState(orderState);
                            return orderDto;
                        }))
                .sorted(Comparator.comparing(OrderDto::getOrderId).reversed())
                .collect(Collectors.toList());
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
    public boolean changeOrderState(String orderid, String original, String changed) {
        OrderType originalType = OrderType.valueOf(original.toUpperCase());
        OrderType changedType = OrderType.valueOf(changed.toUpperCase());
        OrderState orderState = orderStore.retriveOrderStateFromOrderid(orderid, originalType);
        OrderState changedState = orderStateMap.get(changedType).get();
        changedState.setOrderId(orderState.getOrderId());
        changedState.setOrderState(changedType.getDefaultStatus());
        changedState.setStateChangeDate(getToday());
        changedState.setUserId(orderState.getUserId());
        orderStore.deleteOrderState(orderid, originalType);
        orderStore.createOrderState(changedState, changedType);
        return true;
    }

    @Override
    public Map<String, Integer> getOrderStateCount(String userid, String type) {
        OrderType requestOrderType = OrderType.valueOf(type.toUpperCase());
        OrderState state = orderStateMap.get(requestOrderType).get();
        return state.getStatusTypes().stream().collect(Collectors.toMap(statusTypeEnum -> statusTypeEnum.toString(),
                    statusTypeEnum -> orderStore.getStateCountFromUserid(userid, statusTypeEnum.getStatus(), requestOrderType)));
    }

    private String getToday(){
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(today);
    }
}
