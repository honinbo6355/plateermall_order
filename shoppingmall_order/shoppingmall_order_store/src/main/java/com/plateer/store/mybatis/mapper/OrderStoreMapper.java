package com.plateer.store.mybatis.mapper;

import com.plateer.domain.OrderDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderStoreMapper {

    public List<OrderDto> findAll(String userid);
}
