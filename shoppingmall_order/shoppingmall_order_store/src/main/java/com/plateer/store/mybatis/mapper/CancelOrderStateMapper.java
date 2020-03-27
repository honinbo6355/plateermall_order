package com.plateer.store.mybatis.mapper;

import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.CancelOrderState;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CancelOrderStateMapper {

    CancelOrderState getCancelOrderFromOrderid(String orderid);
}