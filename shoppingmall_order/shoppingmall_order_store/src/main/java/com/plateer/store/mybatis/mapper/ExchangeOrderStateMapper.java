package com.plateer.store.mybatis.mapper;

import com.plateer.domain.orderstate.ExchangeOrderState;

public interface ExchangeOrderStateMapper extends OrderStateMapper{
    @Override
    ExchangeOrderState getOrderFromOrderid(String orderid);
}
