package com.plateer.store.mybatis.mapper.payment;

import com.plateer.domain.OrderCardPayment;

public interface OrderCardPaymentMapper {

    OrderCardPayment retriveOneCardPayment(String orderId);

    void saveOrderCardPayment(OrderCardPayment orderCardPayment);
}
