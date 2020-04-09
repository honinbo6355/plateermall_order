package com.plateer.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class OrderDiscountPrice {

    private String orderId;
    private String discountName;
    private String discountPrice;

}
