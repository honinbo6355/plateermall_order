package com.plateer.service.impl;

import com.plateer.OrderServiceTestApplication;
import com.plateer.domain.OrderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServiceTestApplication.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;


    @Test
    public void testAnything(){
        System.out.println(orderService.getOrderStateCount("testid", "cancel"));
        System.out.println(orderService.getOrderStateCount("testid", "normal"));
        System.out.println(orderService.getOrderStateCount("testid", "return"));
        System.out.println(orderService.getOrderStateCount("testid", "exchange"));
    }

    @Test
    public void findOrderFromOrderIdTest(){

        String orderid = "202000001";
        System.out.println(orderService.findOrderFromOrderId(orderid));
    }

    @Test
    public void findOrderListFromUseridTest(){

        orderService.findOrderListFromUserid("testid", "normal").stream().forEach(System.out::println);
    }

    @Test
    public void createOrderTest(){
        OrderDto testDto = new OrderDto(" ", "testid", "1203917702", 1, "19000", "2020-04-06", "250", null);
//        실제 데이터 생성이므로 주의
//        orderService.createOrder(testDto);
//        orderService.createOrder(testDto);
//        orderService.createOrder(testDto);
    }

    @Test
    public void changeOrderStateTest(){
        String orderid = "202000045";
        String originalType = "normal";
        String changedType = "cancel";
//        실제 데이터 조작 주의
//        orderService.changeOrderState(orderid, originalType, changedType);
    }

    @Test
    public void getOrderStateCountTest(){

        System.out.println(orderService.getOrderStateCount("testid", "cancel"));
    }

    @Test
    public void getSpecificStatusOrderListTest(){
        System.out.println(orderService.getSpecificStatusOrderList("cancel", "cancel-request", "testid"));
    }

}
