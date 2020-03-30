package com.plateer.store.mybatis;

import com.plateer.MyBatisOrderTestApplication;
import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.CancelOrderState;
import com.plateer.domain.orderstate.NormalOrderState;
import com.plateer.domain.orderstate.OrderType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyBatisOrderTestApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MyBatisOrderStoreTest {

    @Autowired
    MyBatisOrderStore orderStore;
    OrderDto testOrderDto;

    @Before
    public void before(){
        OrderState testOrderState = new CancelOrderState("202000001", "2020-03-26", "취소요청");
        this.testOrderDto = new OrderDto("202000001", "testid", "1203917700",1 ,"35000", "2020-03-25","사이즈 : 235", null);
    }

    @Test
    public void findAllTest(){
        //폼에서 h2 빼보기 상관없음
        Assert.assertEquals(6, orderStore.findAllOrderFromUserid("testid").size());
    }

    @Test
    public void retrieveOne(){
//        Assert.assertEquals(testOrderDto.getOrderId(), orderStore.retriveOne("202000001").getOrderId());
    }

    @Test
    public void retriveOrderFromMapTest(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(date));
    }

    @Test
    public void createOrderIdTest(){
//        OrderState normalOrderState = new NormalOrderState(testOrderDto.getOrderId(), testOrderDto.getOrderDate(), NormalOrderState.StatusType.SHIPPING.getStatus());
//        orderStore.createOrder(testOrderDto, normalOrderState);
        System.out.println(OrderType.valueOf("NORMAL").toString());
        System.out.println(OrderType.valueOf("NORMAL").getDefaultStatus());
    }

    @Test
    public void createAndRemoveOrderStateTest(){
        OrderState normalOrderState = new NormalOrderState("1234", testOrderDto.getOrderDate(), NormalOrderState.StatusType.SHIPPING.getStatus());
        orderStore.createOrderState(normalOrderState, OrderType.NORMAL);
        orderStore.deleteOrderState(normalOrderState.getOrderId(), OrderType.NORMAL);
        orderStore.createOrderState(normalOrderState, OrderType.CANCEL);
        orderStore.deleteOrderState(normalOrderState.getOrderId(), OrderType.CANCEL);
        orderStore.createOrderState(normalOrderState, OrderType.EXCHANGE);
        orderStore.deleteOrderState(normalOrderState.getOrderId(), OrderType.EXCHANGE);
        orderStore.createOrderState(normalOrderState, OrderType.RETURN);
        orderStore.deleteOrderState(normalOrderState.getOrderId(), OrderType.RETURN);
    }
}
