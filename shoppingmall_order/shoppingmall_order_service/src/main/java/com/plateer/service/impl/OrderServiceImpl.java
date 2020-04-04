package com.plateer.service.impl;

import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.*;
import com.plateer.service.OrderService;
import com.plateer.store.mybatis.MyBatisOrderStateStore;
import com.plateer.store.mybatis.MyBatisOrderStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Supplier;

import static java.util.stream.Collectors.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private MyBatisOrderStore orderStore;
    private MyBatisOrderStateStore orderStateStore;
    private Map<OrderType, Supplier<OrderState>> orderStateMap;

    public OrderServiceImpl(MyBatisOrderStore orderStore, MyBatisOrderStateStore orderStateStore){

        this.orderStore = orderStore;
        this.orderStateStore = orderStateStore;

        this.orderStateMap = new HashMap<>();
        this.orderStateMap.put(OrderType.NORMAL, NormalOrderState::new);
        this.orderStateMap.put(OrderType.CANCEL, CancelOrderState::new);
        this.orderStateMap.put(OrderType.EXCHANGE, ExchangeOrderState::new);
        this.orderStateMap.put(OrderType.RETURN, ReturnOrderState::new);
    }

    @Override
    public List<OrderDto> findAllOrderFromUserId(String userid) {
        //
        return orderStore.findAllOrderFromUserid(userid);
    }

    @Override
    public OrderDto findOrderFromOrderId(String orderid) {
        //
        return orderStore.retriveOne(orderid);
    }

    @Override
    public List<OrderDto> findOrderListFromUserid(String userid, String orderType) {
        //
        OrderType requestOrderType = OrderType.valueOf(orderType.toUpperCase());
        List<OrderState> stateList = orderStateStore.findOrderStateListFromUserid(userid, requestOrderType); //

        return getOrderDtoListFromOrderStateList(stateList);
    }

    @Override
    public int createOrder(OrderDto orderDto) {

        int newOrderId = orderStore.getNewOrderid();
        orderDto.setOrderId(Integer.toString(newOrderId));
        OrderState normalOrderState = new NormalOrderState(orderDto.getOrderId(), orderDto.getOrderDate(), OrderType.NORMAL.getDefaultStatus(), orderDto.getUserId());
        orderStore.createOrder(orderDto);
        orderStateStore.createOrderState(normalOrderState, OrderType.NORMAL);

        return newOrderId;
    }

    @Override
    public boolean changeOrderState(String orderid, String original, String changed) {

        OrderType originalType = OrderType.valueOf(original.toUpperCase());
        OrderType changedType = OrderType.valueOf(changed.toUpperCase());
        OrderState orderState = orderStateStore.retriveOrderStateFromOrderid(orderid, originalType);
        OrderState changedState = orderStateMap.get(changedType).get();
        //리펙토링 가능
        changedState.setOrderId(orderState.getOrderId());
        changedState.setOrderState(changedType.getDefaultStatus());
        changedState.setStateChangeDate(getToday());
        changedState.setUserId(orderState.getUserId());
        orderStateStore.deleteOrderState(orderid, originalType);
        orderStateStore.createOrderState(changedState, changedType);
        return true;
    }

    @Override
    public Map<String, Integer> getOrderStateCount(String userid, String type) {

        OrderType requestOrderType = OrderType.valueOf(type.toUpperCase());
        OrderState state = orderStateMap.get(requestOrderType).get();

        return state.getStatusTypes().stream().collect(toMap(statusTypeEnum -> statusTypeEnum.toString(),
                    statusTypeEnum -> orderStateStore.getStateCountFromUserid(userid, statusTypeEnum.getStatus(), requestOrderType)));
    }

    @Override
    public List<OrderDto> getSpecificStateOrderList(String state, String specific, String userid) {
        //리펙토링 가능
        OrderType requestOrderType = OrderType.valueOf(state.toUpperCase());
        OrderState requestState = orderStateMap.get(requestOrderType).get();
        String parsedSpecificStatus = specific.toUpperCase().replaceAll("-", "_");

        List<OrderState> specificOrderStateList = requestState.getStatusTypes().stream()
                .filter(statusTypeEnum -> statusTypeEnum.toString().equals(parsedSpecificStatus))
                .flatMap(statusTypeEnum -> orderStateStore.findSpecificOrderStateListFromUserid(userid, statusTypeEnum.getStatus(), requestOrderType).stream())
                .collect(toList());

        return getOrderDtoListFromOrderStateList(specificOrderStateList);
    }

    private String getToday(){
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(today);
    }

    private List<OrderDto> getOrderDtoListFromOrderStateList(List<OrderState> orderStateList){

        String userid = orderStateList.get(0).getUserId();
        List<OrderDto> orderList = orderStore.findAllOrderFromUserid(userid);
        return orderStateList.stream()
                .flatMap(orderState -> orderList.stream()
                        .filter(orderDto -> orderDto.getOrderId().equals(orderState.getOrderId()))
                        .map(orderDto -> {
                            orderDto.setOrderState(orderState);
                            return orderDto;
                        })
                ).sorted(Comparator.comparing(OrderDto::getOrderId).reversed())
                .collect(toList());
    }

}
