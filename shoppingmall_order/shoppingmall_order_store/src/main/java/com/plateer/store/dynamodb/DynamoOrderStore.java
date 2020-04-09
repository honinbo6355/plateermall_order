package com.plateer.store.dynamodb;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.google.gson.Gson;
import com.plateer.domain.*;
import com.plateer.domain.orderstate.NormalOrderState;
import com.plateer.store.dynamodb.dto.DdbOrderDto;
import com.plateer.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class DynamoOrderStore {

    @Autowired
    private Environment environment;

    public void test() {
        System.out.println(environment.getProperty("aws.dynamodb.access.key.id"));
        System.out.println(environment.getProperty("aws.dynamodb.secret.access.key"));
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(environment.getProperty("aws.dynamodb.access.key.id"), environment.getProperty("aws.dynamodb.secret.access.key"))))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();

        DynamoDB ddb = new DynamoDB(client);
        Table table = ddb.getTable("shoppingmall-order");
        String orderId = "1";
        String userId = "123123@";
        //데이터 생성 코드
//        PutItemOutcome outcome = table.putItem(new Item().withPrimaryKey("orderId", orderId).withString("userId", userId));
//        GetItemSpec getItemSpec = new GetItemSpec().withPrimaryKey();
        Item item = table.getItem(new PrimaryKey("orderId", orderId));
        System.out.println(item.toJSONPretty());

        //

        /* JSON으로 Create */
        OrderDto testDto = createTestOrderDto();
        Gson gson = new Gson();
        /* create
        String json = gson.toJson(testDto);
        Item jsonItem = Item.fromJSON(json);
        table.putItem(jsonItem);
        * */

        /* JSON으로 READ */
        Item retrivedItem = table.getItem("orderId", "3");
        String retrivedJSON = retrivedItem.toJSON();
//        OrderDto retrivedOrderDto = gson.fromJson(retrivedJSON, OrderDto.class);
//        System.out.println(retrivedOrderDto);




        DynamoDBMapper mapper = new DynamoDBMapper(client);

        /* CREATE*/
//        DdbOrderDto testDto = new DdbOrderDto("3", "123123@");
//        mapper.save(testDto);

        DdbOrderDto partitionKey = new DdbOrderDto();
        partitionKey.setOrderId("2");

        /*READ*/
        System.out.println(mapper.load(DdbOrderDto.class, "2"));

        DynamoDBQueryExpression<DdbOrderDto> queryExpression = new DynamoDBQueryExpression<DdbOrderDto>().withHashKeyValues(partitionKey);
        mapper.query(DdbOrderDto.class, queryExpression).stream().forEach(System.out::println);

    }









    private OrderDto createTestOrderDto() {
        OrderDeliveryInfo deliveryInfo = new OrderDeliveryInfo("3", "성남시 중원구 도촌남로22",
                "성남시 중원구 도촌동", "108-1501", "나윤주먹", "없으면 가져가세요", "010-1234-1234", "010-1234-1234");
        List<OrderDiscountPrice> discountPriceList = new ArrayList<>();
        discountPriceList.add(new OrderDiscountPrice("3", "카드할인", "2000"));
        discountPriceList.add(new OrderDiscountPrice("3", "포인트", "500"));
        OrderPaymentInfo paymentInfo = new OrderPaymentInfo(
                new OrderOriginalPrice("3", "500000", "2500"),
                discountPriceList,
                new OrderCardPayment("3", "우리카드", "일시불")
        );
        OrderPointInfo pointInfo = new OrderPointInfo("3", "77", "200");
        NormalOrderState orderState = new NormalOrderState("3", "2020-04-09", "주문접수", "123123@");
        OrderDto orderDto = new OrderDto("3", "123123@", "1203973748", 1, "179100",
                OrderUtil.getToday(), "250", orderState, deliveryInfo, paymentInfo, pointInfo);

        return orderDto;
    }
}
