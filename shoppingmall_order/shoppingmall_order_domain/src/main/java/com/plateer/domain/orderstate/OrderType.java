package com.plateer.domain.orderstate;

public enum OrderType {
    NORMAL("주문접수"), CANCEL("취소요청"), EXCHANGE("교환요청"), RETURN("반품요청");

    final private String defaultStatus;

    OrderType(String defaultStatus){
        this.defaultStatus = defaultStatus;
    }

    public String getDefaultStatus() {
        return this.defaultStatus;
    }
}
