package com.plateer.store.mybatis;

import com.plateer.domain.OrderDeliveryInfo;
import com.plateer.domain.OrderPointInfo;
import com.plateer.store.mybatis.mapper.info.OrderDeliveryInfoMapper;
import com.plateer.store.mybatis.mapper.info.OrderPointInfoMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MyBatisOrderInfoStore {

    private OrderDeliveryInfoMapper deliveryInfoMapper;
    private OrderPointInfoMapper pointInfoMapper;

    public MyBatisOrderInfoStore(OrderDeliveryInfoMapper deliveryInfoMapper, OrderPointInfoMapper pointInfoMapper) {

        this.deliveryInfoMapper = deliveryInfoMapper;
        this.pointInfoMapper = pointInfoMapper;

    }

    public OrderDeliveryInfo retriveOrderDeliveryInfo(String orderId) {

        return deliveryInfoMapper.retriveOrderDeliveryInfo(orderId);
    }

    public void saveOrderDeliveryInfo(OrderDeliveryInfo orderDeliveryInfo) {

        deliveryInfoMapper.saveOrderDeliveryInfo(orderDeliveryInfo);
    }

    public OrderPointInfo retriveOrderPointInfo(String orderId) {

        return pointInfoMapper.retriveOrderPointInfo(orderId);
    }

    public void saveOrderPointInfo(OrderPointInfo orderPointInfo) {

        pointInfoMapper.saveOrderPointInfo(orderPointInfo);
    }
}
