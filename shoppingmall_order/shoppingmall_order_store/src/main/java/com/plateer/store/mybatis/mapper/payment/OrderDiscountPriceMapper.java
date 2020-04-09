package com.plateer.store.mybatis.mapper.payment;

import com.plateer.domain.OrderDiscountPrice;

import java.util.List;

public interface OrderDiscountPriceMapper {

    List<OrderDiscountPrice> retriveDiscountPriceList(String orderId);

    void saveDiscountPrice(OrderDiscountPrice discountPrice);
}
