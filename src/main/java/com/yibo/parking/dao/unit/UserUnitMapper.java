package com.yibo.parking.dao.unit;

import com.yibo.parking.entity.unit.UserUnit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserUnitMapper {


    List<UserUnit> findList(UserUnit userUnit);

    UserUnit get(String id);

    int insert(UserUnit userUnit);

    int update(UserUnit userUnit);

    int delete(String id);

    UserUnit getByUser(@Param("userId") String userId);
}
