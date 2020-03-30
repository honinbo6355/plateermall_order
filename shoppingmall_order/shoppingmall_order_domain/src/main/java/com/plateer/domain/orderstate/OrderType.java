package com.plateer.domain.orderstate;

public enum OrderType {
    NORMAL("배송중"), CANCEL("취소 요청"), EXCHANGE("교환 신청"), RETURN("반품 신청");

    final private String defaultStatus;

    OrderType(String defaultStatus){
        this.defaultStatus = defaultStatus;
    }

    public String getDefaultStatus() {
        return this.defaultStatus;
    }
}
