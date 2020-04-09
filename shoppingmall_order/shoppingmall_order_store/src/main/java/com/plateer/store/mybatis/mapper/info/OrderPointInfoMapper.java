package com.plateer.store.mybatis.mapper.info;

import com.plateer.domain.OrderPointInfo;

public interface OrderPointInfoMapper {

    OrderPointInfo retriveOrderPointInfo(String orderId);

    void saveOrderPointInfo(OrderPointInfo orderPointInfo);
}
