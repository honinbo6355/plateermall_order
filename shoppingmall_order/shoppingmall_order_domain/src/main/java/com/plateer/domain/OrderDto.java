package com.plateer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

	private String orderId;
	private String userId;
	private String goodsId;
	private  int goodsCount;
	private String orderPrice;
	private String orderDate;
	private String selectedOptions;

	private OrderState orderState;
	
}
