package com.plateer.domain;

public interface OrderState {

	String getOrderId();
	
	String getStateChangeDate();
	
	String getOrderState();

	String getUserId();
	
	void setOrderId(String orderId);
	
	void setStateChangeDate(String stateChangeDate);
	
	void setOrderState(String orderState);

	void setUserId(String userId);
}
