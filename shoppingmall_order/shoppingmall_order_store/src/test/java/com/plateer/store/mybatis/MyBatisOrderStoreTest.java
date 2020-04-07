package com.plateer.store.mybatis;

import com.plateer.MyBatisOrderTestApplication;
import com.plateer.domain.OrderCardPayment;
import com.plateer.domain.OrderDiscountPrice;
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


    @Test
    public void test(){
//        orderPaymentStore.saveOriginalPriceInfo(new OrderOriginalPrice("202000032", "500000", "2500"));
//        System.out.println(orderPaymentStore.retriveOneOriginalPriceInfo("202000032"));
//        orderPaymentStore.saveOrderCardPayment(new OrderCardPayment("202000032", "우리카드", "일시불"));
//        System.out.println(orderPaymentStore.retriveOneOrderCardPayment("202000032"));
//        List<OrderDiscountPrice> discountPriceList = new ArrayList<>();
//        discountPriceList.add(new OrderDiscountPrice("202000032", "카드할인", "2000"));
//        discountPriceList.add(new OrderDiscountPrice("202000032", "포인트", "500"));
//
//        discountPriceList.stream().forEach(orderDiscountPrice -> orderPaymentStore.saveOrderDiscountPrice(orderDiscountPrice));
//        System.out.println(orderPaymentStore.retriveOrderDiscountPriceList("202000032"));
    }
}
