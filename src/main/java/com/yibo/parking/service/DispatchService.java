package com.yibo.parking.service;

import com.yibo.parking.entity.car.Dispatch;

import java.util.List;

public interface DispatchService {

    List<Dispatch> findList(Dispatch dispatch);

    Dispatch get(Dispatch dispatch);

    int save(Dispatch dispatch);

    int delete(Dispatch dispatch);
}
