package com.plateer.store.mybatis;

//import com.plateer.ShoppingmallOrderBootApplication;
import com.plateer.store.OrderStore;
import com.plateer.MyBatisOrderTestApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MyBatisOrderTestApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MyBatisOrderStoreTest {

    @Autowired
    OrderStore myBatisOrderStore;

    @Before
    public void before(){

    }

    @Test
    public void test(){

        Assert.assertTrue(true);
    }
}
