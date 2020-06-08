package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.TransformData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransformDataMapper {

    List<TransformData> findList(TransformData data);

    List<TransformData> getLocation();
}
