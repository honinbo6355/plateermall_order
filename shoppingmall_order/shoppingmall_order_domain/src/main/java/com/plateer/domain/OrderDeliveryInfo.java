package com.plateer.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class OrderDeliveryInfo {

    private String orderId;
    private String roadAddress;
    private String zipcodeAddress;
    private String remainAddress;
    private String receiverName;
    private String message;
    private String contactNumber;
    private String phoneNumber;
}
