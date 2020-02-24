package com.yibo.parking.service;

import com.yibo.parking.entity.car.MaintainOrder;

import java.util.List;

public interface MaintainOrderService {
    List<MaintainOrder> getOrders();

    int save(MaintainOrder order);

    int check(String id, String status);
}
