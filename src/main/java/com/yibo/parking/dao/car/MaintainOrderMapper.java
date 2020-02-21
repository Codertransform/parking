package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.MaintainOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaintainOrderMapper {
    List<MaintainOrder> getOrders();

    int insert(MaintainOrder order);

    int update(MaintainOrder order);
}