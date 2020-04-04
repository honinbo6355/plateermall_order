package com.plateer.store.mybatis;

import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.OrderType;
import com.plateer.store.mybatis.mapper.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisOrderStateStore {

    private Map<OrderType, OrderStateMapper> mapperMap;

    public MyBatisOrderStateStore(NormalOrderStateMapper normalOrderStateMapper, CancelOrderStateMapper cancelOrderStateMapper,
                                  ExchangeOrderStateMapper exchangeOrderStateMapper, ReturnOrderStateMapper returnOrderStateMapper){

        this.mapperMap = new HashMap<>();
        this.mapperMap.put(OrderType.NORMAL, normalOrderStateMapper);
        this.mapperMap.put(OrderType.CANCEL, cancelOrderStateMapper);
        this.mapperMap.put(OrderType.EXCHANGE, exchangeOrderStateMapper);
        this.mapperMap.put(OrderType.RETURN, returnOrderStateMapper);
    }

    public OrderState retriveOrderStateFromOrderid(String orderid, OrderType typeEnum) {

        return mapperMap.get(typeEnum).getOrderStateFromOrderid(orderid);
    }

    public void createOrderState(OrderState orderState, OrderType typeEnum){

        mapperMap.get(typeEnum).createNewOrderState(orderState);
    }

    public boolean deleteOrderState(String orderid, OrderType typeEnum) {

        mapperMap.get(typeEnum).deleteOrderState(orderid);

        return true;
    }

    public List<OrderState> findOrderStateListFromUserid(String userid, OrderType typeEnum) {

        return mapperMap.get(typeEnum).getOrderStateListFromUserid(userid);
    }

    public int getStateCountFromUserid(String userid, String state, OrderType typeEnum) {

        HashMap<String, String> params = new HashMap<>();
        params.put("state", state);
        params.put("userid", userid);

        return mapperMap.get(typeEnum).countOrderState(params);
    }

    public List<OrderState> findSpecificOrderStateListFromUserid(String userid, String state, OrderType typeEnum) {

        HashMap<String, String> params = new HashMap<>();
        params.put("state", state);
        params.put("userid", userid);

        return mapperMap.get(typeEnum).getSpecificOrderStateList(params);
    }
}
