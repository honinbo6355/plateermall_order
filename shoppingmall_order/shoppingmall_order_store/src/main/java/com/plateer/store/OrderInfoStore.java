package com.plateer.store;

import com.plateer.domain.OrderDeliveryInfo;
import com.plateer.domain.OrderPointInfo;

public interface OrderInfoStore {

    OrderDeliveryInfo retriveOrderDeliveryInfo(String orderId);

    void saveOrderDeliveryInfo(OrderDeliveryInfo orderDeliveryInfo);

    OrderPointInfo retriveOrderPointInfo(String orderId);

    void saveOrderPointInfo(OrderPointInfo orderPointInfo);
}
