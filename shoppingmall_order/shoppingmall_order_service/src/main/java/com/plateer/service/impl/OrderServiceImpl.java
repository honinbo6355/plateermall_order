package com.plateer.service.impl;

import com.plateer.domain.OrderDto;
import com.plateer.domain.OrderState;
import com.plateer.domain.orderstate.StatusTypeEnum;
import com.plateer.domain.orderstate.*;
import com.plateer.service.OrderService;
import com.plateer.store.mybatis.MyBatisOrderStateStore;
import com.plateer.store.mybatis.MyBatisOrderStore;
import com.plateer.util.OrderStateFactory;
import com.plateer.util.OrderUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.stream.Collectors.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private MyBatisOrderStore orderStore;
    private MyBatisOrderStateStore orderStateStore;

    public OrderServiceImpl(MyBatisOrderStore orderStore, MyBatisOrderStateStore orderStateStore) {

        this.orderStore = orderStore;
        this.orderStateStore = orderStateStore;
    }

    /*
    OrderState가 null인 불완전한 OrderDto를 반환한다.
     */
    @Override
    public OrderDto findOrderFromOrderId(String orderid) {

        return orderStore.retriveOne(orderid);
    }

    @Override
    public List<OrderDto> findOrderListFromUserid(String userid, String orderType) {

        OrderType requestOrderType = OrderUtil.getOrderTypeByString(orderType);
        List<OrderState> stateList = orderStateStore.findOrderStateListFromUserid(userid, requestOrderType);
        List<OrderDto> completeOrderDto = getCompleteOrderDtoListFromOrderStateList(stateList);

        return completeOrderDto;
    }

    @Override
    public int createOrder(OrderDto orderDto) {

        int newOrderId = orderStore.getNewOrderid();
        orderDto.setOrderId(Integer.toString(newOrderId));
        orderStore.createOrder(orderDto);
        OrderState normalOrderState = new NormalOrderState(orderDto.getOrderId(), orderDto.getOrderDate(), OrderType.NORMAL.getDefaultStatus(), orderDto.getUserId());
        orderStateStore.createOrderState(normalOrderState, OrderType.NORMAL);

        return newOrderId;
    }

    /*
    기존 OrderState에 저장되어 있는 데이터를 삭제하고 변경된 OrderState에 해당하는 데이터를 생성한다.
     */
    @Override
    public boolean changeOrderState(String orderid, String originalOrderType, String changedOrderType) {

        OrderType originalType = OrderUtil.getOrderTypeByString(originalOrderType);
        OrderState originalOrderState = orderStateStore.retriveOrderStateFromOrderid(orderid, originalType);
        orderStateStore.deleteOrderState(orderid, originalType);
        OrderType changedType = OrderUtil.getOrderTypeByString(changedOrderType);
        OrderState changedState = OrderStateFactory.getNewOrderStateInstance(changedType);
        changedState.setOrderId(originalOrderState.getOrderId());
        changedState.setOrderState(changedType.getDefaultStatus());
        changedState.setStateChangeDate(OrderUtil.getToday());
        changedState.setUserId(originalOrderState.getUserId());
        orderStateStore.createOrderState(changedState, changedType);

        return true;
    }

    @Override
    public Map<String, Integer> getOrderStateCount(String userid, String orderType) {

        OrderType requestOrderType = OrderUtil.getOrderTypeByString(orderType);
        Map<String, Integer> userOrderStatusCountMap = getUserOrderStatusCountMap(userid, requestOrderType);

        return userOrderStatusCountMap;
    }

    @Override
    public List<OrderDto> getSpecificStatusOrderList(String orderType, String specific, String userid) {

        OrderType requestOrderType = OrderUtil.getOrderTypeByString(orderType);
        String parsedSpecificStatus = OrderUtil.parsingStatusUrlPatternToEnumPattern(specific);
        List<OrderState> specificOrderStateList = getSpecificOrderStateList(userid, parsedSpecificStatus, requestOrderType);
        System.out.println(specificOrderStateList);
        List<OrderDto> completeOrderDtoList = getCompleteOrderDtoListFromOrderStateList(specificOrderStateList);

        return completeOrderDtoList;
    }


    /*
    OrderState 형식의 List를 인자로 받아 완전한 OrderDto 객체의 List로 반환한다.
    같은 userid를 가지고 있는 stateList를 인자로 주어야 정상적으로 작동한다.
     */
    private List<OrderDto> getCompleteOrderDtoListFromOrderStateList(List<OrderState> orderStateList) {

        if (orderStateList.isEmpty()) return Collections.EMPTY_LIST;

        String userid = orderStateList.get(0).getUserId();
        List<OrderDto> orderList = orderStore.findAllOrderFromUserid(userid);
        List<OrderDto> completeOrderDtoList = orderStateList.stream()
                .flatMap(orderState -> orderList.stream()
                        .filter(orderDto -> orderDto.getOrderId().equals(orderState.getOrderId()))
                        .map(orderDto -> {
                            orderDto.setOrderState(orderState);
                            return orderDto;
                        })
                ).sorted(Comparator.comparing(OrderDto::getOrderId).reversed())
                .collect(toList());

        return completeOrderDtoList;
    }

    /*
    OrderType의 특정 StatusType의 리스트를 반환한다.
    매개변수 parsedSpecificStatus가 OrderType으로 반환되는 OrderState의 StatusType의 enum이름과 같은 리스트를 반환한다.
     */
    private List<OrderState> getSpecificOrderStateList(String userid, String parsedSpecificStatus, OrderType requestOrderType) {

        OrderState requestState = OrderStateFactory.getNewOrderStateInstance(requestOrderType);
        List<StatusTypeEnum> statusTypeEnumList = requestState.getStatusTypes();
        List<OrderState> specificOrderStateList = statusTypeEnumList.stream()
                .filter(statusTypeEnum -> statusTypeEnum.toString().equals(parsedSpecificStatus))
                .flatMap(statusTypeEnum -> orderStateStore.findSpecificOrderStateListFromUserid(userid, statusTypeEnum.getStatus(), requestOrderType).stream())
                .sorted(Comparator.comparing(OrderState::getOrderId).reversed())
                .collect(toList());

        return specificOrderStateList;
    }

    /*
     OrderState 구현체에 정의되어있는 enum의 이름을 key, 그 enum의 실제 status값에 해당하는 데이터의 카운트를 value로 가지는 Map을 반환한다.
     ex) {CANCEL_REQUEST:1, CANCEL_COMPLETE:3}
     */
    private Map<String, Integer> getUserOrderStatusCountMap(String userid, OrderType requestOrderType) {

        OrderState requestOrderState = OrderStateFactory.getNewOrderStateInstance(requestOrderType);
        List<StatusTypeEnum> statusTypeEnumList = requestOrderState.getStatusTypes();
        Map<String, Integer> userOrderStatusCount = statusTypeEnumList.stream().collect(toMap(
                statusTypeEnum -> statusTypeEnum.toString(),
                statusTypeEnum -> orderStateStore.getStateCountFromUserid(userid, statusTypeEnum.getStatus(), requestOrderType)));

        return userOrderStatusCount;
    }
}
