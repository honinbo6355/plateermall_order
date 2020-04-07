package com.plateer.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class OrderCardPaymentInfo {

    private String orderId;
    private String cardName;
    private int installments;

}
