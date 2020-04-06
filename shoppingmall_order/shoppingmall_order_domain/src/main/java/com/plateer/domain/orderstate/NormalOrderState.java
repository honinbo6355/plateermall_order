package com.plateer.domain.orderstate;

import com.plateer.domain.OrderState;

import com.plateer.domain.StatusTypeEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NormalOrderState implements OrderState{

	private String orderId;
	private String stateChangeDate;
	private String orderState;
	private String userId;

	public enum StatusType implements StatusTypeEnum {
		ORDER_COMPLETE("주문접수"), PAYMENT_COMPLETE("결제완료"), SHIPPING_READY("배송준비중"), SHIPPING("배송중"), SHIPPING_COMPLETE("배송완료");

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
		return userId;
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
