package com.plateer.service;

import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;

import java.util.List;
import java.util.Map;

public interface OrderStateService {

    OrderDto findOrderFromOrderId(String orderid);

    List<OrderDto> findOrderListFromUserid(String userid, String orderType);

    OrderState getOrderStateFromOrderId(String orderId, String orderType);

    void createDefaultOrderState(String orderId, String orderDate, String userId);

    boolean changeOrderState(String orderid, String original, String changed);

    Map<String, Integer> getOrderStateCount(String userid, String type);

    List<OrderDto> getSpecificStatusOrderList(String state, String specific, String userid);
}
