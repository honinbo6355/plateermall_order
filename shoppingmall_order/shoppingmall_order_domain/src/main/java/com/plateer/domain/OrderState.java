package com.plateer.domain;

import com.plateer.domain.orderstate.StatusTypeEnum;

import java.util.List;

public interface OrderState {

	List<StatusTypeEnum> getStatusTypes();

	String getOrderId();
	
	String getStateChangeDate();
	
	String getOrderState();

	String getUserId();
	
	void setOrderId(String orderId);
	
	void setStateChangeDate(String stateChangeDate);
	
	void setOrderState(String orderState);

	void setUserId(String userId);
}
