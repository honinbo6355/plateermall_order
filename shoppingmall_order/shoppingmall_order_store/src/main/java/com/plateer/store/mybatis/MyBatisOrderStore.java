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

    private Map<Enum<OrderType>, OrderStateMapper> mapperMap;

    public MyBatisOrderStore(OrderStoreMapper orderStoreMapper, NormalOrderStateMapper normalOrderStateMapper, CancelOrderStateMapper cancelOrderStateMapper,
                             ExchangeOrderStateMapper exchangeOrderStateMapper, ReturnOrderStateMapper returnOrderStateMapper){

        this.orderStoreMapper = orderStoreMapper;
        this.mapperMap = new HashMap<>();
        this.mapperMap.put(OrderType.NORMAL, normalOrderStateMapper);
        this.mapperMap.put(OrderType.CANCEL, cancelOrderStateMapper);
        this.mapperMap.put(OrderType.EXCHANGE, exchangeOrderStateMapper);
        this.mapperMap.put(OrderType.RETURN, returnOrderStateMapper);
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
    public List<OrderDto> retriveOrderList(String userid, Enum<OrderType> typeEnum) {
        OrderStateMapper mapper = this.mapperMap.get(typeEnum);
        //이거 스트림 두개 연결해서 가능할거같은데??
        return findAll(userid).stream()
                .map(orderDto -> {
                    //optional?
                    OrderState normalState = mapper.getOrderFromOrderid(orderDto.getOrderId());
                    orderDto.setOrderState(normalState);
                    return orderDto;
                })
                .filter(orderDto -> orderDto.getOrderState() != null)
                .collect(Collectors.toList());
    }
}
