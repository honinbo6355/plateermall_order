package com.plateer.util;

import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class OrderStateFactory {

    private static Map<OrderType, Supplier<OrderState>> orderStateMap;

    static {
        orderStateMap = new HashMap<>();
        orderStateMap.put(OrderType.NORMAL, NormalOrderState::new);
        orderStateMap.put(OrderType.CANCEL, CancelOrderState::new);
        orderStateMap.put(OrderType.EXCHANGE, ExchangeOrderState::new);
        orderStateMap.put(OrderType.RETURN, ReturnOrderState::new);

    }

    public static OrderState getNewOrderStateInstance(OrderType orderType) {

        return orderStateMap.get(orderType).get();
    }
}
