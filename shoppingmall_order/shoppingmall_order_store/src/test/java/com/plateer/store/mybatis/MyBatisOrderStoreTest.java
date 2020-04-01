package com.plateer.store.mybatis;

import com.plateer.MyBatisOrderTestApplication;
import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyBatisOrderTestApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MyBatisOrderStoreTest {

    @Autowired
    MyBatisOrderStore orderStore;
    OrderDto testOrderDto;

    @Before
    public void before(){
        OrderState testOrderState = new CancelOrderState("202000001", "2020-03-26", "취소요청", "testid");
        this.testOrderDto = new OrderDto("202000001", "testid", "1203917700",1 ,"35000", "2020-03-25","사이즈 : 235", null);
    }

    @Test
    public void findAllTest(){
        //폼에서 h2 빼보기 상관없음
//        Assert.assertEquals(6, orderStore.findAllOrderFromUserid("testid").size());
    }

    @Test
    public void retrieveOne(){

    }

    @Test
    public void retriveOrderFromMapTest(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(date));
    }

    @Test
    public void orderStateCountTest(){
        NormalOrderState normalOrderState = new NormalOrderState();
        CancelOrderState cancelOrderState = new CancelOrderState();
        ExchangeOrderState exchangeOrderState = new ExchangeOrderState();
        ReturnOrderState returnOrderState = new ReturnOrderState();
        List<String> normalTypeStrings = normalOrderState.getStatusTypes().stream().map(statusTypeEnum -> statusTypeEnum.getStatus()).collect(Collectors.toList());
        List<String> cancelTypeStrings = cancelOrderState.getStatusTypes().stream().map(statusTypeEnum -> statusTypeEnum.getStatus()).collect(Collectors.toList());
        List<String> exchangeTypeStrings = exchangeOrderState.getStatusTypes().stream().map(statusTypeEnum -> statusTypeEnum.getStatus()).collect(Collectors.toList());
        List<String> returnTypeStrings = returnOrderState.getStatusTypes().stream().map(statusTypeEnum -> statusTypeEnum.getStatus()).collect(Collectors.toList());

    }

    @Test
    public void pleasPassPlease(){
        System.out.println(orderStore.findOrderStateListFromUserid("testid", OrderType.NORMAL));
        System.out.println(orderStore.findOrderStateListFromUserid("testid", OrderType.CANCEL));
        System.out.println(orderStore.findOrderStateListFromUserid("testid", OrderType.EXCHANGE));
        System.out.println(orderStore.findOrderStateListFromUserid("testid", OrderType.RETURN));
    }
}
