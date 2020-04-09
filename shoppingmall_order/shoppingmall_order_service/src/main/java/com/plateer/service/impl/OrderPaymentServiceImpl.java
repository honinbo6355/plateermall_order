package com.plateer.service.impl;

import com.plateer.domain.OrderCardPayment;
import com.plateer.domain.OrderDiscountPrice;
import com.plateer.domain.OrderOriginalPrice;
import com.plateer.domain.OrderPaymentInfo;
import com.plateer.service.OrderPaymentService;
import com.plateer.store.OrderPaymentStore;
import com.plateer.store.mybatis.MyBatisOrderPaymentStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderPaymentServiceImpl implements OrderPaymentService {

    private OrderPaymentStore orderPaymentStore;

    public OrderPaymentServiceImpl(MyBatisOrderPaymentStore orderPaymentStore) {

        this.orderPaymentStore = orderPaymentStore;
    }

    @Override
    public OrderPaymentInfo getOrderPaymentInfo(String orderid) {

        OrderOriginalPrice originalPrice = orderPaymentStore.retriveOneOriginalPriceInfo(orderid);
        List<OrderDiscountPrice> discountPrice = orderPaymentStore.retriveOrderDiscountPriceList(orderid);
        OrderCardPayment cardPayment = orderPaymentStore.retriveOneOrderCardPayment(orderid);
        OrderPaymentInfo orderPaymentInfo = new OrderPaymentInfo(originalPrice, discountPrice, cardPayment);

        return orderPaymentInfo;
    }

    @Override
    public void saveOrderPaymentInfo(OrderPaymentInfo orderPaymentInfo) {

        orderPaymentStore.saveOriginalPriceInfo(orderPaymentInfo.getOrderOriginalPrice());
        saveOrderDiscountPriceList(orderPaymentInfo.getOrderDiscountPriceList());
        orderPaymentStore.saveOrderCardPayment(orderPaymentInfo.getOrderCardPayment());

    }

    private void saveOrderDiscountPriceList(List<OrderDiscountPrice> orderDiscountPriceList) {

        orderDiscountPriceList.stream().forEach(this::saveOrderDiscountPrice);
    }

    private void saveOrderDiscountPrice(OrderDiscountPrice orderDiscountPrice) {

        orderPaymentStore.saveOrderDiscountPrice(orderDiscountPrice);
    }
}
