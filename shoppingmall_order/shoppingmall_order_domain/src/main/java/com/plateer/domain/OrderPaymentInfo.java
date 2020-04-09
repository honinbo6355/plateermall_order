package com.plateer.domain;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentInfo {

    private int originalPrice;
    private int discountPrice;
    private int paymentPrice;

    private OrderOriginalPrice orderOriginalPrice;
    private List<OrderDiscountPrice> orderDiscountPriceList;
    private OrderCardPayment orderCardPayment;

    public OrderPaymentInfo(OrderOriginalPrice orderOriginalPrice, List<OrderDiscountPrice> orderDiscountPriceList, OrderCardPayment orderCardPayment) {

        this.orderOriginalPrice = orderOriginalPrice;
        this.orderDiscountPriceList = orderDiscountPriceList;
        this.orderCardPayment = orderCardPayment;

        this.originalPrice = Integer.parseInt(orderOriginalPrice.getGoodsPrice()) + Integer.parseInt(orderOriginalPrice.getShippingPrice());
        this.discountPrice = orderDiscountPriceList.stream()
                .map(OrderDiscountPrice::getDiscountPrice)
                .mapToInt(Integer::parseInt)
                .sum();
        this.paymentPrice = this.originalPrice - this.discountPrice;
    }
}
