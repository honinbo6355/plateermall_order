package com.plateer.store.mybatis;

import com.plateer.domain.OrderDto;
import com.plateer.store.OrderStore;
import com.plateer.store.mybatis.mapper.OrderStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyBatisOrderStore implements OrderStore {

    @Autowired
    private OrderStoreMapper orderStoreMapper;

    public MyBatisOrderStore(OrderStoreMapper orderStoreMapper){
        this.orderStoreMapper = orderStoreMapper;
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

}
