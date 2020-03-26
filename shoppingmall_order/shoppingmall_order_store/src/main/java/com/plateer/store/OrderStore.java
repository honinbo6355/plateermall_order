package com.plateer.store;

import com.plateer.domain.OrderDto;

import java.util.List;

public interface OrderStore {

    public List<OrderDto> findAll(String userid);
}
