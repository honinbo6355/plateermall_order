package com.plateer.service.impl;

import com.plateer.domain.OrderDeliveryInfo;
import com.plateer.domain.OrderPointInfo;
import com.plateer.service.OrderInfoService;
import com.plateer.store.OrderInfoStore;
import com.plateer.store.mybatis.MyBatisOrderInfoStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    private OrderInfoStore orderInfoStore;

    public OrderInfoServiceImpl(MyBatisOrderInfoStore orderInfoStore) {

        this.orderInfoStore = orderInfoStore;
    }

    @Override
    public OrderDeliveryInfo getOrderDeliveryInfo(String orderid) {

        return orderInfoStore.retriveOrderDeliveryInfo(orderid);
    }

    @Override
    public void saveOrderDeliveryInfo(OrderDeliveryInfo orderDeliveryInfo) {

        orderInfoStore.saveOrderDeliveryInfo(orderDeliveryInfo);
    }

    @Override
    public OrderPointInfo getOrderPointInfo(String orderid) {

        return orderInfoStore.retriveOrderPointInfo(orderid);
    }

    @Override
    public void saveOrderPointInfo(OrderPointInfo orderPointInfo) {

        orderInfoStore.saveOrderPointInfo(orderPointInfo);
    }
}
