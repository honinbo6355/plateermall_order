package com.plateer.service.impl;

import com.plateer.OrderServiceTestApplication;
import com.plateer.domain.OrderCardPayment;
import com.plateer.domain.OrderDiscountPrice;
import com.plateer.domain.OrderOriginalPrice;
import com.plateer.domain.OrderPaymentInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServiceTestApplication.class)
public class OrderPaymentServiceTest {

    @Autowired
    private OrderPaymentServiceImpl orderPaymentService;

    @Test
    public void getOrderPaymentInfoTest() {

        System.out.println(orderPaymentService.getOrderPaymentInfo("202000106"));
    }

    // 실제 데이터 생성이므로 주의
    @Test
    public void saveOrderPaymentInfoTest() {
        List<OrderDiscountPrice> discountPriceList = new ArrayList<>();
        discountPriceList.add(new OrderDiscountPrice("202000033", "카드할인", "2000"));
        discountPriceList.add(new OrderDiscountPrice("202000033", "포인트", "500"));
        OrderPaymentInfo paymentInfo = new OrderPaymentInfo(new OrderOriginalPrice("202000033", "500000", "2500"),
                discountPriceList, new OrderCardPayment("202000033", "우리카드", "일시불"));
//        orderPaymentService.saveOrderPaymentInfo(paymentInfo);
    }
}
