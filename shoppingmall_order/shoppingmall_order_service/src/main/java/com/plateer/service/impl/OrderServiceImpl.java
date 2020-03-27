package com.plateer.service.impl;

import com.plateer.domain.OrderDto;
import com.plateer.service.OrderService;
import com.plateer.store.OrderStore;
import com.plateer.store.mybatis.MyBatisOrderStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private MyBatisOrderStore orderStore;

    public OrderServiceImpl(MyBatisOrderStore orderStore){
        this.orderStore = orderStore;
    }


    @Override
    public List<OrderDto> findAllOrderFromUserId(String userid) {
        return orderStore.findAll(userid);
    }

    @Override
    public OrderDto findOrderFromOrderId(String orderid) {
        return orderStore.retriveOne(orderid);
    }

    @Override
    public List<OrderDto> findNormalOrderListFromUserid(String userid) {
        return orderStore.retriveNormalOrderList(userid);
    }

    @Override
    public List<OrderDto> findCancelOrderListFromUserid(String userid) {
        return orderStore.retriveCancelOrderList(userid);
    }

    @Override
    public List<OrderDto> findExchangeOrderListFromUserid(String userid) {
        return orderStore.retriveExchangeOrderList(userid);
    }

    @Override
    public List<OrderDto> findReturnOrderListFromUserid(String userid) {
        return orderStore.retriveReturnOrderList(userid);
    }


}
