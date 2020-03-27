package com.plateer.store.mybatis;

import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.NormalOrderState;
import com.plateer.store.OrderStore;
import com.plateer.store.mybatis.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class MyBatisOrderStore implements OrderStore {

    private OrderStoreMapper orderStoreMapper;
    private NormalOrderStateMapper normalOrderStateMapper;
    private CancelOrderStateMapper cancelOrderStateMapper;
    private ExchangeOrderStateMapper exchangeOrderStateMapper;
    private ReturnOrderStateMapper returnOrderStateMapper;

    public MyBatisOrderStore(OrderStoreMapper orderStoreMapper, NormalOrderStateMapper normalOrderStateMapper, CancelOrderStateMapper cancelOrderStateMapper,
                             ExchangeOrderStateMapper exchangeOrderStateMapper, ReturnOrderStateMapper returnOrderStateMapper){

        this.orderStoreMapper = orderStoreMapper;
        this.normalOrderStateMapper = normalOrderStateMapper;
        this.cancelOrderStateMapper = cancelOrderStateMapper;
        this.exchangeOrderStateMapper = exchangeOrderStateMapper;
        this.returnOrderStateMapper = returnOrderStateMapper;
    }

    @Override
    public List<OrderDto> findAll(String userid) {
        System.out.println("Store");
        return orderStoreMapper.findAll(userid);
    }

    @Override
    public OrderDto retriveOne(String orderid) {
        return orderStoreMapper.retriveOne(orderid);
    }

    @Override
    public List<OrderDto> retriveNormalOrderList(String userid) {

        List<OrderDto> normalOrderStateOrders = findAll(userid).stream()
                                                .map(orderDto -> {
                                                    OrderState normalState = normalOrderStateMapper.getNormalOrderFromOrderid(orderDto.getOrderId());
                                                    orderDto.setOrderState(normalState);
                                                    return orderDto;
                                                })
                                                .filter(orderDto -> orderDto.getOrderState() != null)
                                                .collect(Collectors.toList());
        return normalOrderStateOrders;
    }

    @Override
    public List<OrderDto> retriveCancelOrderList(String userid) {
        List<OrderDto> cancelOrderStateOrders = findAll(userid).stream()
                .map(orderDto -> {
                    OrderState cancelState = cancelOrderStateMapper.getCancelOrderFromOrderid(orderDto.getOrderId());
                    orderDto.setOrderState(cancelState);
                    return orderDto;
                })
                .filter(orderDto -> orderDto.getOrderState() != null)
                .collect(Collectors.toList());
        return cancelOrderStateOrders;
    }

    @Override
    public List<OrderDto> retriveExchangeOrderList(String userid) {
        List<OrderDto> exchangeOrderStateOrders = findAll(userid).stream()
                .map(orderDto -> {
                    OrderState exchangeState = exchangeOrderStateMapper.getExchangeOrderFromOrderid(orderDto.getOrderId());
                    orderDto.setOrderState(exchangeState);
                    return orderDto;
                })
                .filter(orderDto -> orderDto.getOrderState() != null)
                .collect(Collectors.toList());
        return exchangeOrderStateOrders;
    }

    @Override
    public List<OrderDto> retriveReturnOrderList(String userid) {
        List<OrderDto> returnOrderStateOrders = findAll(userid).stream()
                .map(orderDto -> {
                    OrderState returnState = returnOrderStateMapper.getReturnOrderFromOrderid(orderDto.getOrderId());
                    orderDto.setOrderState(returnState);
                    return orderDto;
                })
                .filter(orderDto -> orderDto.getOrderState() != null)
                .collect(Collectors.toList());
        return returnOrderStateOrders;
    }


}
