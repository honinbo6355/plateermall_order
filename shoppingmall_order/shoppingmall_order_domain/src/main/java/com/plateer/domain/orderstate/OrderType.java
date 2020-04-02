package com.plateer.domain.orderstate;

public enum OrderType {
    NORMAL(NormalOrderState.StatusType.ORDER_COMPLETE.getStatus()),
    CANCEL(CancelOrderState.StatusType.CANCEL_REQUEST.getStatus()),
    EXCHANGE(ExchangeOrderState.StatusType.EXCHANGE_REQUEST.getStatus()),
    RETURN(ReturnOrderState.StatusType.RETURN_REQUEST.getStatus());

    final private String defaultStatus;

    OrderType(String defaultStatus){
        this.defaultStatus = defaultStatus;
    }

    public String getDefaultStatus() {
        return this.defaultStatus;
    }
}
