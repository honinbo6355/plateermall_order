package com.plateer.service.impl;

import com.plateer.OrderServiceTestApplication;
import com.plateer.domain.OrderDeliveryInfo;
import com.plateer.domain.OrderPointInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServiceTestApplication.class)
public class OrderInfoServiceTest {

    @Autowired
    OrderInfoServiceImpl orderInfoService;

    @Test
    public void getOrderDeliveryInfoTest() {

        System.out.println(orderInfoService.getOrderDeliveryInfo("202000032"));
    }

    //실제 데이터 생성 주의
    @Test
    public void saveOrderDeliveryInfoTest() {

//        orderInfoService.saveOrderDeliveryInfo(new OrderDeliveryInfo("202000033", "성남시 중원구 도촌남로22",
//                "성남시 중원구 도촌동", "108-1501", "나윤주먹", "문 앞에 놓아주세요"));
    }

    @Test
    public void getOrderPointInfoTest() {

        System.out.println(orderInfoService.getOrderPointInfo("202000032"));
    }

    //실제 데이터 생성 주의
    @Test
    public void saveOrderPointInfoTest() {

//        orderInfoService.saveOrderPointInfo(new OrderPointInfo("202000033", "77", "200"));
    }
}
