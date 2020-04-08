package com.plateer.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderPointInfo {

    private String orderId;
    private String orderComplete;
    private String writeComment;

}
