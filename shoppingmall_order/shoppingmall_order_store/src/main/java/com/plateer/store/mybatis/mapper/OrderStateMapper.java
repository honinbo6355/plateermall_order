package com.plateer.store.mybatis.mapper;

import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.NormalOrderState;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderStateMapper {

    OrderState getOrderFromOrderid(String orderid);
    int createNewOrderState(OrderState orderState);
}
