package com.plateer.store.mybatis;

import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.OrderType;
import com.plateer.store.OrderStateStore;
import com.plateer.store.mybatis.mapper.state.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MyBatisOrderStateStore implements OrderStateStore {

    private Map<OrderType, OrderStateMapper> mapperMap;

    public MyBatisOrderStateStore(NormalOrderStateMapper normalOrderStateMapper, CancelOrderStateMapper cancelOrderStateMapper,
                                  ExchangeOrderStateMapper exchangeOrderStateMapper, ReturnOrderStateMapper returnOrderStateMapper){

        this.mapperMap = new HashMap<>();
        this.mapperMap.put(OrderType.NORMAL, normalOrderStateMapper);
        this.mapperMap.put(OrderType.CANCEL, cancelOrderStateMapper);
        this.mapperMap.put(OrderType.EXCHANGE, exchangeOrderStateMapper);
        this.mapperMap.put(OrderType.RETURN, returnOrderStateMapper);
    }

    @Override
    public OrderState retriveOrderStateFromOrderid(String orderid, OrderType typeEnum) {

        return mapperMap.get(typeEnum).getOrderStateFromOrderid(orderid);
    }

    @Override
    public void createOrderState(OrderState orderState, OrderType typeEnum){

        mapperMap.get(typeEnum).createNewOrderState(orderState);
    }

    @Override
    public boolean deleteOrderState(String orderid, OrderType typeEnum) {

        mapperMap.get(typeEnum).deleteOrderState(orderid);

        return true;
    }

    @Override
    public List<OrderState> findOrderStateListFromUserid(String userid, OrderType typeEnum) {

        return mapperMap.get(typeEnum).getOrderStateListFromUserid(userid);
    }

    @Override
    public int getStateCountFromUserid(String userid, String state, OrderType typeEnum) {

        HashMap<String, String> params = new HashMap<>();
        params.put("state", state);
        params.put("userid", userid);

        return mapperMap.get(typeEnum).countOrderState(params);
    }

    @Override
    public List<OrderState> findSpecificOrderStateListFromUserid(String userid, String state, OrderType typeEnum) {

        HashMap<String, String> params = new HashMap<>();
        params.put("state", state);
        params.put("userid", userid);

        return mapperMap.get(typeEnum).getSpecificOrderStateList(params);
    }
}
