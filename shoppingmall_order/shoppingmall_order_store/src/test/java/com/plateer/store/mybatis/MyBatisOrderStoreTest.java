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
    public void createOrderIdTest(){
//        OrderState normalOrderState = new NormalOrderState(testOrderDto.getOrderId(), testOrderDto.getOrderDate(), NormalOrderState.StatusType.SHIPPING.getStatus());
//        orderStore.createOrder(testOrderDto, normalOrderState);
        System.out.println(OrderType.valueOf("NORMAL").toString());
        System.out.println(OrderType.valueOf("NORMAL").getDefaultStatus());
        List<OrderState> stateList = new ArrayList<>();
        stateList.add(new NormalOrderState("1", "1", "1", "1"));
        stateList.add(new NormalOrderState("2", "1", "1", "1"));
        stateList.add(new NormalOrderState("3", "1", "1", "1"));

        List<OrderDto> orderList = new ArrayList<>();
        orderList.add(new OrderDto("1", "id", "3", 1, "1", "1", "1", null));
        orderList.add(new OrderDto("2", "id", "3", 1, "1", "1", "1", null));
        orderList.add(new OrderDto("4", "id", "3", 1, "1", "1", "1", null));
        orderList.add(new OrderDto("3", "id", "3", 1, "1", "1", "1", null));
        orderList.add(new OrderDto("5", "id", "3", 1, "1", "1", "1", null));
        List<OrderDto> testList = stateList.stream()
                .flatMap(orderState -> {
                    System.out.println(orderState);
                        return orderList.stream()
                        .filter(orderDto -> {
                            System.out.println(orderDto);
                            return orderDto.getOrderId() == orderState.getOrderId();
                        }).map(orderDto -> {
                            orderDto.setOrderState(orderState);
                            return orderDto;
                        });
                })
                .collect(Collectors.toList());

        List<OrderDto> testList2 = stateList.stream()
                .flatMap(orderState -> orderList.stream()
                        .filter(orderDto -> orderDto.getOrderId() == orderState.getOrderId())
                        .map(orderDto -> {
                            orderDto.setOrderState(orderState);
                            return orderDto;
                        }))
                .collect(Collectors.toList());

        System.out.println(testList);
        System.out.println(testList2);
        System.out.println("-----------------------------------");
//        List<OrderDto> testList2 = orderList.stream()
//                .filter(orderDto -> {
//                    System.out.println(orderDto);
//                    return stateList.stream().anyMatch(orderState -> {
//                        System.out.println(orderState);
//                        return orderState.getOrderId() == orderDto.getOrderId();
//                    });
//                })
//                .collect(Collectors.toList());
//        System.out.println(testList2);
//        List<OrderDto> testList3 = orderList.stream()
//                .filter(orderDto -> stateList.stream()
//                        .filter(orderState -> orderState.getOrderId() == orderDto.getOrderId())
//                        .anyMatch(orderState -> orderState.getOrderId() == orderDto.getOrderId()))
//                .collect(Collectors.toList());
//        stateList.stream()
//                .flatMap(orderState -> orderStore.findAllOrderFromUserid(userid).stream().filter(orderStore -> orderState.getOrderId() == orderStore.getOrderId()))
//                .collect(Collectors.toList());
    }

    @Test
    public void pleasPassPlease(){
        //DB 컬럼 만들고 testid로 다 넣고 테스트해보기
        System.out.println(orderStore.findOrderStateListFromUserid("testid", OrderType.NORMAL));
        System.out.println(orderStore.findOrderStateListFromUserid("testid", OrderType.CANCEL));
        System.out.println(orderStore.findOrderStateListFromUserid("testid", OrderType.EXCHANGE));
        System.out.println(orderStore.findOrderStateListFromUserid("testid", OrderType.RETURN));
    }
}
