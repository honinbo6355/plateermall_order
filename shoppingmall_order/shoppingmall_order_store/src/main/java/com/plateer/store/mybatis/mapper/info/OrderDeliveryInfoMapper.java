package com.plateer.store.mybatis.mapper.info;

import com.plateer.domain.OrderDeliveryInfo;

public interface OrderDeliveryInfoMapper {

    OrderDeliveryInfo retriveOrderDeliveryInfo(String userId);

    void saveOrderDeliveryInfo(OrderDeliveryInfo orderDeliveryInfo);
}
