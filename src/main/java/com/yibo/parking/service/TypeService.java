package com.yibo.parking.service;

import com.yibo.parking.entity.car.Type;

import java.util.List;

public interface TypeService {

    List<Type> getTypes();

    Type get(String id);

    int save(Type type);

    int del(String id);

    int dels(String[] ids);
}
