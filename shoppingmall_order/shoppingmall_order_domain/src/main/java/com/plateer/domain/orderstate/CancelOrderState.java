package com.plateer.domain.orderstate;

import com.plateer.domain.OrderState;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CancelOrderState implements OrderState{

	private String orderId;
	private String stateChangeDate;
	private String orderState;
	private String userId;

	public enum StatusType implements StatusTypeEnum {
		CANCEL_REQUEST("취소요청"), CANCEL_COMPLETE("취소완료");

		final private String status;

		StatusType(String status){
			this.status = status;
		}

		@Override
		public String getStatus(){ return this.status; }
	}


	@Override
	public List<StatusTypeEnum> getStatusTypes() {
		return Arrays.asList(StatusType.values());
	}

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
