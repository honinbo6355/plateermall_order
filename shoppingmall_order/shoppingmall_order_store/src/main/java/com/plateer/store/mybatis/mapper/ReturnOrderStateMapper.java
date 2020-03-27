package com.plateer.store.mybatis.mapper;

import com.plateer.domain.orderstate.ExchangeOrderState;
import com.plateer.domain.orderstate.ReturnOrderState;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReturnOrderStateMapper {
    ReturnOrderState getReturnOrderFromOrderid(String orderid);
}