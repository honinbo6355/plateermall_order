package com.plateer.service.impl;

import com.plateer.OrderServiceTestApplication;
import com.plateer.domain.OrderDto;
import com.plateer.domain.StatusTypeEnum;
import com.plateer.domain.orderstate.NormalOrderState;
import com.plateer.domain.orderstate.OrderType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServiceTestApplication.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Before
    public void before(){

    }

    @Test
    public void test(){
//        NormalOrderState nstate = new NormalOrderState();
//        nstate.getStatusTypes().stream().forEach(statusTypeEnum -> System.out.println(statusTypeEnum.toString()));
//        System.out.println(orderService.findOrderListFromUserid("testid", "normal"));
//        orderService.changeOrderState("202000034", "cancel", "normal");
        System.out.println(orderService.getOrderStateCount("testid", "cancel"));
        System.out.println(orderService.getOrderStateCount("testid", "normal"));
        System.out.println(orderService.getOrderStateCount("testid", "return"));
        System.out.println(orderService.getOrderStateCount("testid", "exchange"));
    }

    @Test
    public void createTestOrder(){
        OrderDto testDto = new OrderDto(" ", "testid", "1203917702", 1, "19000", "2020-03-31", "250", null);
        orderService.createOrder(testDto);
//        orderService.createOrder(testDto);
//        orderService.createOrder(testDto);
        String test = "payment-complete";
        System.out.println(test.toUpperCase().replaceAll("-", "_"));
    }

    @Test
    public void changeTest(){
        Stream.iterate(new int[]{0, 1}, array -> new int[]{array[1], array[0] + array[1]})
                .limit(20)
                .map(array -> array[0])
                .forEach(System.out::println);
    }

    @Test
    public void enumTest(){
        List<NormalOrderState.StatusType> testlist = Arrays.asList(NormalOrderState.StatusType.values());
        testlist.stream().forEach(statusType -> System.out.println(statusType.name()));
        System.out.println(testlist);
        List<StatusTypeEnum> list = Arrays.asList(NormalOrderState.StatusType.values());
        list.stream().forEach(statusTypeEnum -> System.out.println(statusTypeEnum.getStatus()));
        System.out.println(list);
    }

    @Test
    public void getOrderStateCountTest(){
        System.out.println(orderService.getOrderStateCount("testid", "cancel"));
    }

    @Test
    public void getSpecificStateOrderListTest(){
        orderService.getSpecificStateOrderList("normal", "order-complete", "testid").stream().forEach(System.out::println);
    }

    @Test
    public void findOrderListFromUseridTest(){
        orderService.findOrderListFromUserid("testid", "cancel").stream().forEach(System.out::println);
    }
}
