package com.yibo.parking.service;

import com.yibo.parking.entity.car.Car;

import java.util.List;

public interface CarService {
    List<Car> getCars(String typeId, String logmin, String logmax, String cardId);

    int save(Car car);

    int del(String id);

    int dels(String[] ids);

    Car get(String id);

    int stop(String id);

    int start(String id);
}
