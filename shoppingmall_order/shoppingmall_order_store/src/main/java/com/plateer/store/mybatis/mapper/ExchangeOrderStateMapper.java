package com.plateer.store.mybatis.mapper;

import com.plateer.domain.orderstate.ExchangeOrderState;

public interface ExchangeOrderStateMapper {
    ExchangeOrderState getExchangeOrderFromOrderid(String orderid);
}
