package com.plateer.domain.orderstate;

import com.plateer.domain.OrderState;

public class ReturnOrderState implements OrderState{

	private String orderId;
	private String stateChangeDate;
	private String orderState;
	
	@Override
	public String getOrderId() {
		// 
		return this.orderId;
	}
	
	@Override
	public String getStateChangeDate() {
		// 
		return this.stateChangeDate;
	}

	@Override
	public String getOrderState() {
		// 
		return this.orderState;
	}

	@Override
	public void setOrderId(String orderId) {
		// 
		this.orderId = orderId;
	}

	@Override
	public void setStateChangeDate(String stateChangeDate) {
		//
		this.stateChangeDate = stateChangeDate;
	}

	@Override
	public void setOrderState(String orderState) {
		// 
		this.orderState = orderState;
	}

}
