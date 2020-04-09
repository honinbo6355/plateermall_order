package com.plateer.service.impl;

import com.plateer.domain.*;
import com.plateer.domain.orderstate.OrderType;
import com.plateer.service.OrderInfoService;
import com.plateer.service.OrderPaymentService;
import com.plateer.service.OrderService;
import com.plateer.service.OrderStateService;
import com.plateer.store.OrderStore;
import com.plateer.store.mybatis.MyBatisOrderStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderStateService orderStateService;
    private OrderPaymentService orderPaymentService;
    private OrderInfoService orderInfoService;

    private MyBatisOrderStore orderStore;

    public OrderServiceImpl(OrderStateService orderStateService, OrderPaymentService orderPaymentService, OrderInfoService orderInfoService, MyBatisOrderStore orderStore) {

        this.orderStateService = orderStateService;
        this.orderPaymentService = orderPaymentService;
        this.orderInfoService = orderInfoService;
        this.orderStore = orderStore;
    }

    @Override
    public int createOrder(OrderDto orderDto) {

        String newOrderId = Integer.toString(orderStore.getNewOrderid());
        OrderDto completeOrderDto = setNewOrderIdToOrderDto(orderDto, newOrderId);


        orderStore.createOrder(completeOrderDto);
        orderStateService.createDefaultOrderState(completeOrderDto.getOrderId(), completeOrderDto.getOrderDate(), completeOrderDto.getUserId());
        orderPaymentService.saveOrderPaymentInfo(completeOrderDto.getOrderPaymentInfo());
        orderInfoService.saveOrderPointInfo(completeOrderDto.getOrderPointInfo());
        orderInfoService.saveOrderDeliveryInfo(completeOrderDto.getOrderDeliveryInfo());

        return Integer.parseInt(newOrderId);
    }

    /*
    OrderState, OrderDeliveryInfo, OrderPayment, OrderPointInfo가 null인 불완전한 OrderDto를 반환한다.
     */
    @Override
    public OrderDto getOrderDto(String orderId) {

        return orderStore.retriveOne(orderId);
    }

    @Override
    public OrderDto getFullOrder(String orderId) {

        OrderDto requestOrder = orderStore.retriveOne(orderId);
        OrderState requestOrderState = orderStateService.getOrderStateFromOrderId(orderId, OrderType.NORMAL.toString());
        OrderDeliveryInfo requestDeliveryInfo = orderInfoService.getOrderDeliveryInfo(orderId);
        OrderPointInfo requestPointInfo = orderInfoService.getOrderPointInfo(orderId);
        OrderPaymentInfo requestPaymentInfo = orderPaymentService.getOrderPaymentInfo(orderId);

        requestOrder.setOrderState(requestOrderState);
        requestOrder.setOrderDeliveryInfo(requestDeliveryInfo);
        requestOrder.setOrderPointInfo(requestPointInfo);
        requestOrder.setOrderPaymentInfo(requestPaymentInfo);

        return requestOrder;
    }

    private OrderDto setNewOrderIdToOrderDto(OrderDto orderDto, String newOrderId) {

        orderDto.setOrderId(newOrderId);
        orderDto.getOrderDeliveryInfo().setOrderId(newOrderId);
        orderDto.getOrderPointInfo().setOrderId(newOrderId);
        orderDto.getOrderPaymentInfo().getOrderCardPayment().setOrderId(newOrderId);
        orderDto.getOrderPaymentInfo().getOrderOriginalPrice().setOrderId(newOrderId);
        orderDto.getOrderPaymentInfo().getOrderDiscountPriceList().stream().forEach(orderDiscountPrice -> orderDiscountPrice.setOrderId(newOrderId));

        return orderDto;
    }
}
