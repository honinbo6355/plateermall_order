package com.plateer.store.mybatis;

import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.OrderType;
import com.plateer.store.OrderStore;
import com.plateer.store.mybatis.mapper.*;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MyBatisOrderStore implements OrderStore {

    private OrderStoreMapper orderStoreMapper;
    private NormalOrderStateMapper normalOrderStateMapper;
    private CancelOrderStateMapper cancelOrderStateMapper;
    private ExchangeOrderStateMapper exchangeOrderStateMapper;
    private ReturnOrderStateMapper returnOrderStateMapper;
    private Map<OrderType, OrderStateMapper> mapperMap;

    public MyBatisOrderStore(OrderStoreMapper orderStoreMapper, NormalOrderStateMapper normalOrderStateMapper, CancelOrderStateMapper cancelOrderStateMapper,
                             ExchangeOrderStateMapper exchangeOrderStateMapper, ReturnOrderStateMapper returnOrderStateMapper){

        this.orderStoreMapper = orderStoreMapper;
        this.normalOrderStateMapper = normalOrderStateMapper;
        this.cancelOrderStateMapper = cancelOrderStateMapper;
        this.exchangeOrderStateMapper = exchangeOrderStateMapper;
        this.returnOrderStateMapper = returnOrderStateMapper;
        this.mapperMap = new HashMap<>();
        this.mapperMap.put(OrderType.NORMAL, normalOrderStateMapper);
        this.mapperMap.put(OrderType.CANCEL, cancelOrderStateMapper);
        this.mapperMap.put(OrderType.EXCHANGE, exchangeOrderStateMapper);
        this.mapperMap.put(OrderType.RETURN, returnOrderStateMapper);
    }

    @Override
    public List<OrderDto> findAllOrderFromUserid(String userid) {
        System.out.println("Store");
        return orderStoreMapper.findAllOrderFromUserid(userid);
    }

    @Override
    public OrderDto retriveOne(String orderid) {
        return orderStoreMapper.retriveOne(orderid);
    }

    @Override
    public OrderState retriveOrderStateFromOrderid(String orderid, OrderType typeEnum) {
        return mapperMap.get(typeEnum).getOrderStateFromOrderid(orderid);
    }

    @Override
    public void createOrder(OrderDto orderDto, OrderState orderState) {
        orderStoreMapper.createNewOrder(orderDto);
        mapperMap.get(OrderType.NORMAL).createNewOrderState(orderState);
    }

    @Override
    public int getNewOrderid() {
        return orderStoreMapper.getNewOrderId();
    }

    @Override
    public boolean createOrderState(OrderState orderState, OrderType typeEnum) {
        mapperMap.get(typeEnum).createNewOrderState(orderState);
        return true;
    }

    @Override
    public boolean deleteOrderState(String orderid, OrderType typeEnum) {
        mapperMap.get(typeEnum).deleteOrderState(orderid);
        return true;
    }

}
