package com.plateer.service.impl;

import com.plateer.OrderServiceTestApplication;
import com.plateer.domain.orderstate.OrderType;
import org.junit.Assert;
import org.junit.Before;
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

    @Before
    public void before(){

    }

    @Test
    public void test(){
        orderService.changeOrderState("202000006", OrderType.NORMAL, OrderType.CANCEL);
    }

    @Test
    public void getOrderFromOrderidTest(){
        Assert.assertEquals(3, orderService.findOrderListFromUserid("testid", OrderType.NORMAL).size());
    }

}
