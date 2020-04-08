package com.plateer.store.mybatis;

import com.plateer.MyBatisOrderTestApplication;
import com.plateer.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyBatisOrderTestApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MyBatisOrderStoreTest {

    @Autowired
    MyBatisOrderStore orderStore;
    @Autowired
    MyBatisOrderPaymentStore orderPaymentStore;
    @Autowired
    MyBatisOrderInfoStore orderInfoStore;


    @Test
    public void test(){
//        orderPaymentStore.saveOriginalPriceInfo(new OrderOriginalPrice("202000032", "500000", "2500"));
//        System.out.println(orderPaymentStore.retriveOneOriginalPriceInfo("202000032"));
//        orderPaymentStore.saveOrderCardPayment(new OrderCardPayment("202000032", "우리카드", "일시불"));
//        System.out.println(orderPaymentStore.retriveOneOrderCardPayment("202000032"));
        List<OrderDiscountPrice> discountPriceList = new ArrayList<>();
        discountPriceList.add(new OrderDiscountPrice("202000032", "카드할인", "2000"));
        discountPriceList.add(new OrderDiscountPrice("202000032", "포인트", "500"));
//
//        discountPriceList.stream().forEach(orderDiscountPrice -> orderPaymentStore.saveOrderDiscountPrice(orderDiscountPrice));
//        System.out.println(orderPaymentStore.retriveOrderDiscountPriceList("202000032"));

//        orderInfoStore.saveOrderDeliveryInfo(new OrderDeliveryInfo("202000032", "성남시 중원구 도촌남로22",
//                "성남시 중원구 도촌동", "108-1501", "나윤주먹", "없으면 가져가세요"));
//        orderInfoStore.retriveOrderDeliveryInfo("202000032");
//        orderInfoStore.saveOrderPointInfo(new OrderPointInfo("202000032", "77", "200"));
//        orderInfoStore.retriveOrderPointInfo("202000032");
        OrderPaymentInfo paymentInfo = new OrderPaymentInfo(new OrderOriginalPrice("202000032", "500000", "2500"),
                discountPriceList, new OrderCardPayment("202000032", "우리카드", "일시불"));
        System.out.println(paymentInfo);
    }
}
