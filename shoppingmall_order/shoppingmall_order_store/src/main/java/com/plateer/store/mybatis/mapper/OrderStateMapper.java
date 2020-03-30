package com.plateer.store.mybatis.mapper;

import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.NormalOrderState;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderStateMapper {

    OrderState getOrderStateFromOrderid(String orderid);
    int createNewOrderState(OrderState orderState);
    int deleteOrderState(String orderid);
}
