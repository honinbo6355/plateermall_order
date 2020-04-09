package com.plateer.service;

import com.plateer.domain.OrderDeliveryInfo;
import com.plateer.domain.OrderPointInfo;

public interface OrderInfoService {

    OrderDeliveryInfo getOrderDeliveryInfo(String orderid);

    void saveOrderDeliveryInfo(OrderDeliveryInfo orderDeliveryInfo);

    OrderPointInfo getOrderPointInfo(String orderid);

    void saveOrderPointInfo(OrderPointInfo orderPointInfo);
}
