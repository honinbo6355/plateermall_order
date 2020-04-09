package com.plateer.service.impl;

import com.plateer.OrderServiceTestApplication;
import com.plateer.domain.*;
import com.plateer.domain.orderstate.NormalOrderState;
import com.plateer.util.OrderUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServiceTestApplication.class)
public class OrderServiceTest {

    @Autowired
    OrderServiceImpl orderService;

    @Test
    public void createOrderTest() {
//        System.out.println(orderService.getOrderDto("202000032"));
//        OrderDeliveryInfo deliveryInfo = new OrderDeliveryInfo("", "성남시 중원구 도촌남로22",
//                "성남시 중원구 도촌동", "108-1501", "나윤주먹", "없으면 가져가세요");
//        List<OrderDiscountPrice> discountPriceList = new ArrayList<>();
//        discountPriceList.add(new OrderDiscountPrice("", "카드할인", "2000"));
//        discountPriceList.add(new OrderDiscountPrice("", "포인트", "500"));
//        OrderPaymentInfo paymentInfo = new OrderPaymentInfo(
//                new OrderOriginalPrice("", "500000", "2500"),
//                discountPriceList,
//                new OrderCardPayment("", "우리카드", "일시불")
//        );
//        OrderPointInfo pointInfo = new OrderPointInfo("", "77", "200");
//        OrderDto orderDto = new OrderDto(null, "123123@", "1203973748", 1, "179100",
//                OrderUtil.getToday(), "250", null, deliveryInfo, paymentInfo, pointInfo);

//        orderService.createOrder(orderDto);


    }

    @Test
    public void getOrderTest() {
        System.out.println(orderService.getOrderDto("202000047"));
    }

    @Test
    public void getFullOrderTest() {

        Arrays.stream(OrderPaymentInfo.class.getFields()).forEach(System.out::println);
//        System.out.println(orderService.getFullOrder("202000106"));
    }
}
