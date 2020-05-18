package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.Type;
import com.yibo.parking.entity.car.TypeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TypeInfoMapper {
    List<TypeInfo> getInfos();

    List<TypeInfo> gets(Type type);

    int insert(TypeInfo info);

    int update(TypeInfo info);

    int delete(TypeInfo info);

    List<TypeInfo> findByTypeId(@Param("typeId") String typeId);
}