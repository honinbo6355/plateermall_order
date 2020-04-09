package com.plateer.service;

import com.plateer.domain.OrderPaymentInfo;

public interface OrderPaymentService {

    OrderPaymentInfo getOrderPaymentInfo(String orderid);

    void saveOrderPaymentInfo(OrderPaymentInfo orderPaymentInfo);
}
