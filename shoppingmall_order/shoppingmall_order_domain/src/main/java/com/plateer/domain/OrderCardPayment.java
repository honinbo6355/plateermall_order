package com.plateer.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class OrderCardPayment {

    private String orderId;
    private String cardName;
    private String installments;

}
