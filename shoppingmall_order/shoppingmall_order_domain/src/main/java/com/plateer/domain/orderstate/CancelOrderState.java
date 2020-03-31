package com.plateer.domain.orderstate;

import com.plateer.domain.OrderState;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CancelOrderState implements OrderState{

	private String orderId;
	private String stateChangeDate;
	private String orderState;
	private String userId;
//	private Type type;
//
//	public enum Type {
//		CANCEL, GOING, USE
//	}

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
	public String getUserId() {
		return this.userId;
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

	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
