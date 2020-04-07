package com.plateer.domain;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderPaymentInfo {

    private int originalPrice;
    private int discountPrice;
    private int paymentPrice;

    private OrderOriginalPrice orderOriginalPrice;
    private List<OrderDiscountPrice> orderDiscountPriceList;
    private OrderCardPaymentInfo orderCardPaymentInfo;
}
