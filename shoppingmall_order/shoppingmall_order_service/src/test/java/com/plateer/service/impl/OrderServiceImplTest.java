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
        System.out.println(orderService.getOrderStateCount("testid", OrderType.CANCEL));
        System.out.println(orderService.getOrderStateCount("testid", OrderType.NORMAL));
        System.out.println(orderService.getOrderStateCount("testid", OrderType.RETURN));
        System.out.println(orderService.getOrderStateCount("testid", OrderType.EXCHANGE));
    }

    @Test
    public void createTestOrder(){
        OrderDto testDto = new OrderDto(" ", "testid", "1203917702", 1, "19000", "2020-03-31", "250", null);
//        orderService.createOrder(testDto);
//        orderService.createOrder(testDto);
//        orderService.createOrder(testDto);
    }

    @Test
    public void changeTest(){
//        orderService.changeOrderState("202000002", OrderType.CANCEL, OrderType.CANCEL);
//        orderService.changeOrderState("202000008", OrderType.CANCEL, OrderType.CANCEL);
//        orderService.changeOrderState("202000009", OrderType.CANCEL, OrderType.CANCEL);
//        orderService.changeOrderState("202000010", OrderType.CANCEL, OrderType.CANCEL);
//        orderService.changeOrderState("202000011", OrderType.CANCEL, OrderType.CANCEL);
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
}
