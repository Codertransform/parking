package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.OriginGPSData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OriginDataMapper {

    int insert(OriginGPSData originGPSData);

}
