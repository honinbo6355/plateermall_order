package com.plateer.domain;

public interface OrderState {

	public String getOrderId();
	
	public String getStateChangeDate();
	
	public String getOrderState();
	
	public void setOrderId(String orderId);
	
	public void setStateChangeDate(String stateChangeDate);
	
	public void setOrderState(String orderState);
}
