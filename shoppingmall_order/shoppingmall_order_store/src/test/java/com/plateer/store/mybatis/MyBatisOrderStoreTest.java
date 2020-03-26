package com.plateer.store.mybatis;

//import com.plateer.ShoppingmallOrderBootApplication;
import com.plateer.MyBatisOrderTestApplication;
import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.CancelOrderState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MyBatisOrderTestApplication.class)
@SpringBootTest(classes = MyBatisOrderTestApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MyBatisOrderStoreTest {

    @Autowired
    MyBatisOrderStore OrderStore;
    OrderDto testOrderDto;

    @Before
    public void before(){
        OrderState testOrderState = new CancelOrderState("202000001", "2020-03-26", "취소요청");
        this.testOrderDto = new OrderDto("202000001", "testid", "1203917700",1 ,"35000", "2020-03-25","사이즈 : 235", testOrderState);
    }

    @Test
    public void findAllTest(){
        //폼에서 h2 빼보기 상관없음
        Assert.assertEquals(4, OrderStore.findAll("testid").size());
    }

    @Test
    public void retrieveOne(){
        Assert.assertEquals(testOrderDto.getOrderId(), OrderStore.retriveOne("202000001").getOrderId());
    }


}
