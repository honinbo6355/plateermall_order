package com.plateer.store.mybatis;

import com.plateer.domain.OrderDto;
import com.plateer.store.OrderStore;
import com.plateer.store.mybatis.mapper.OrderStoreMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyBatisOrderStore implements OrderStore {

    private OrderStoreMapper orderStoreMapper;

    public MyBatisOrderStore(OrderStoreMapper orderStoreMapper){
        this.orderStoreMapper = orderStoreMapper;
    }

    @Override
    public List<OrderDto> findAll(String userid) {

        return null;
    }
}
