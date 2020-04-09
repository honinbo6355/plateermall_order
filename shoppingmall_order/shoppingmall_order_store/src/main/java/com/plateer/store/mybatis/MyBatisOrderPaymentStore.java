package com.plateer.store.mybatis;

import com.plateer.domain.OrderCardPayment;
import com.plateer.domain.OrderDiscountPrice;
import com.plateer.domain.OrderOriginalPrice;
import com.plateer.store.OrderPaymentStore;
import com.plateer.store.mybatis.mapper.payment.OrderCardPaymentMapper;
import com.plateer.store.mybatis.mapper.payment.OrderDiscountPriceMapper;
import com.plateer.store.mybatis.mapper.payment.OrderOriginalPriceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyBatisOrderPaymentStore implements OrderPaymentStore {

    private OrderOriginalPriceMapper originalPriceInfoMapper;
    private OrderDiscountPriceMapper discountPriceInfoMapper;
    private OrderCardPaymentMapper cardPaymentInfoMapper;

    public MyBatisOrderPaymentStore(OrderOriginalPriceMapper originalPriceInfoMapper,
                                    OrderDiscountPriceMapper discountPriceInfoMapper,
                                    OrderCardPaymentMapper cardPaymentInfoMapper){

        this.originalPriceInfoMapper = originalPriceInfoMapper;
        this.discountPriceInfoMapper = discountPriceInfoMapper;
        this.cardPaymentInfoMapper = cardPaymentInfoMapper;
    }

    public OrderOriginalPrice retriveOneOriginalPriceInfo(String orderId) {

        return originalPriceInfoMapper.retriveOneOriginalPriceInfo(orderId);
    }

    public void saveOriginalPriceInfo(OrderOriginalPrice originalPrice) {

        originalPriceInfoMapper.saveOriginalPriceInfo(originalPrice);
    }

    public OrderCardPayment retriveOneOrderCardPayment(String orderId) {

        return cardPaymentInfoMapper.retriveOneCardPayment(orderId);
    }

    public void saveOrderCardPayment(OrderCardPayment orderCardPayment) {

        cardPaymentInfoMapper.saveOrderCardPayment(orderCardPayment);
    }

    public List<OrderDiscountPrice> retriveOrderDiscountPriceList(String orderId) {

        return discountPriceInfoMapper.retriveDiscountPriceList(orderId);
    }

    public void saveOrderDiscountPrice(OrderDiscountPrice discountPrice) {

        discountPriceInfoMapper.saveDiscountPrice(discountPrice);
    }
}
