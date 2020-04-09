package com.plateer.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class OrderOriginalPrice {

    private String orderId;
    private String goodsPrice;
    private String shippingPrice;

}
