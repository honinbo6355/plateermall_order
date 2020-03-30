package com.plateer.store.mybatis.mapper;

import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.NormalOrderState;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface NormalOrderStateMapper extends OrderStateMapper {

    @Override
    NormalOrderState getOrderFromOrderid(String orderid);
    @Override
    int createNewOrderState(OrderState orderState);
}
