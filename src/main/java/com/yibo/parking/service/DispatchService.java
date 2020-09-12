package com.yibo.parking.service;

import com.yibo.parking.entity.car.Dispatch;

import java.util.List;
import java.util.Map;

public interface DispatchService {

    List<Dispatch> findList(Dispatch dispatch);

    Dispatch get(Dispatch dispatch);

    Map<String, Object> save(Dispatch dispatch);

    int delete(Dispatch dispatch);
}
