package com.plateer.store.mybatis.mapper.payment;

import com.plateer.domain.OrderOriginalPrice;

public interface OrderOriginalPriceMapper {

    OrderOriginalPrice retriveOneOriginalPriceInfo(String orderId);

    void saveOriginalPriceInfo(OrderOriginalPrice originalPrice);
}
