package com.plateer.store;

import com.plateer.domain.OrderCardPayment;
import com.plateer.domain.OrderDiscountPrice;
import com.plateer.domain.OrderOriginalPrice;

import java.util.List;

public interface OrderPaymentStore {

    OrderOriginalPrice retriveOneOriginalPriceInfo(String orderId);

    void saveOriginalPriceInfo(OrderOriginalPrice originalPrice);

    OrderCardPayment retriveOneOrderCardPayment(String orderId);

    void saveOrderCardPayment(OrderCardPayment orderCardPayment);

    List<OrderDiscountPrice> retriveOrderDiscountPriceList(String orderId);

    void saveOrderDiscountPrice(OrderDiscountPrice discountPrice);
}
