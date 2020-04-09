package com.plateer.store.dynamodb;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

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
        String json = null;
        Item newItem = new Item();
        PutItemOutcome outcome = table.putItem(new Item().withPrimaryKey("orderId", orderId).withString("userId", userId));
//        System.out.println(outcome.getPutItemResult());
//        GetItemSpec getItemSpec = new GetItemSpec().withPrimaryKey();
        Item item = table.getItem(new PrimaryKey("orderId", orderId));
        System.out.println(item.toJSONPretty());

    }
}
