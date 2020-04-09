package com.plateer.store.dynamodb.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@DynamoDBTable(tableName = "shoppingmall-order")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DdbOrderDto {

    @DynamoDBHashKey
    private String orderId;
    private String userId;

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
