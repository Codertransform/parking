package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarMapper {
    @Select("select * from cars")
    List<Car> getCars();
}
