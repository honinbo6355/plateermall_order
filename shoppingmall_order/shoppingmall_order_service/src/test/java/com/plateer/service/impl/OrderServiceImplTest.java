package com.plateer.service.impl;

import com.plateer.OrderServiceTestApplication;
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
        Assert.assertEquals(4 , orderService.findAllOrderFromUserId("testid").size());
    }
}
