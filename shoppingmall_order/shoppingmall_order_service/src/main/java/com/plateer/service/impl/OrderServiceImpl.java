package com.plateer.service.impl;

import com.plateer.domain.OrderDiscountPrice;
import com.plateer.domain.OrderDto;
import com.plateer.store.mybatis.MyBatisOrderStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl {

    private OrderStateServiceImpl orderStateService;
    private OrderPaymentServiceImpl orderPaymentService;
    private OrderInfoServiceImpl orderInfoService;

    private MyBatisOrderStore orderStore;

    public OrderServiceImpl(OrderStateServiceImpl orderStateService, OrderPaymentServiceImpl orderPaymentService, OrderInfoServiceImpl orderInfoService, MyBatisOrderStore orderStore) {

        this.orderStateService = orderStateService;
        this.orderPaymentService = orderPaymentService;
        this.orderInfoService = orderInfoService;
        this.orderStore = orderStore;
    }

    public int createOrder(OrderDto orderDto) {
        // 분리해야할 것
        String newOrderId = Integer.toString(orderStore.getNewOrderid());
        orderDto.setOrderId(newOrderId);
        orderDto.getOrderDeliveryInfo().setOrderId(newOrderId);
        orderDto.getOrderPointInfo().setOrderId(newOrderId);
        orderDto.getOrderPaymentInfo().getOrderCardPayment().setOrderId(newOrderId);
        orderDto.getOrderPaymentInfo().getOrderOriginalPrice().setOrderId(newOrderId);
        orderDto.getOrderPaymentInfo().getOrderDiscountPriceList().stream().forEach(orderDiscountPrice -> orderDiscountPrice.setOrderId(newOrderId));

        orderStore.createOrder(orderDto);
        orderStateService.createDefaultOrderState(orderDto.getOrderId(), orderDto.getOrderDate(), orderDto.getUserId());
        orderPaymentService.saveOrderPaymentInfo(orderDto.getOrderPaymentInfo());
        orderInfoService.saveOrderPointInfo(orderDto.getOrderPointInfo());
        orderInfoService.saveOrderDeliveryInfo(orderDto.getOrderDeliveryInfo());

        return Integer.parseInt(newOrderId);
    }

    /*
    OrderState, OrderDeliveryInfo, OrderPayment, OrderPointInfo가 null인 불완전한 OrderDto를 반환한다.
     */
    public OrderDto getOrderDto(String orderId) {

        return orderStore.retriveOne(orderId);
    }

}
