package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarMapper {
    List<Car> getCars(@Param("typeId") String typeId, @Param("logmin") String logmin, @Param("logmax") String logmax, @Param("cardId") String cardId);

    int insert(Car car);

    int update(Car car);

    int del(String id);

    Car get(String id);

    int stop(String id);

    int start(String id);

    List<Car> getAll();
}
