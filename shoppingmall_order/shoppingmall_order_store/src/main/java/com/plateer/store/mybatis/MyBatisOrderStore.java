package com.plateer.store.mybatis;

import com.plateer.domain.OrderDto;
import com.plateer.store.OrderStore;
import com.plateer.store.mybatis.mapper.*;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class MyBatisOrderStore implements OrderStore {

    private OrderStoreMapper orderStoreMapper;

    public MyBatisOrderStore(OrderStoreMapper orderStoreMapper){

        this.orderStoreMapper = orderStoreMapper;
    }
   @Override
    public List<OrderDto> findAllOrderFromUserid(String userid) {

        return orderStoreMapper.findAllOrderFromUserid(userid);
    }

    @Override
    public OrderDto retriveOne(String orderid) {

        return orderStoreMapper.retriveOne(orderid);
    }

    @Override
    public void createOrder(OrderDto orderDto) {

        orderStoreMapper.createNewOrder(orderDto);
    }

    @Override
    public int getNewOrderid() {

        return orderStoreMapper.getNewOrderId();
    }

}
