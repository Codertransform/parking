package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {

    List<Type> getTypes();

    Type get(String id);

    int insert(Type type);

    int update(Type type);

    int del(String id);

}
