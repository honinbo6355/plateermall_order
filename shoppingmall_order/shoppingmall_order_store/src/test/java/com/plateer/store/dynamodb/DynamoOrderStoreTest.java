package com.plateer.store.dynamodb;

import com.plateer.MyBatisOrderTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyBatisOrderTestApplication.class)
public class DynamoOrderStoreTest {

    @Autowired
    DynamoOrderStore dynamoOrderStore;

    @Test
    public void test() {
        dynamoOrderStore.test();
    }
}
