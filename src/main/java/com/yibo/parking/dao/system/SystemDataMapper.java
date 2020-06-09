package com.yibo.parking.dao.system;

import com.yibo.parking.entity.system.SystemData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemDataMapper {

    List<SystemData> findList(SystemData systemData);

    SystemData get(SystemData systemData);

    int insert(SystemData systemData);

    int update(SystemData systemData);

    int delete(SystemData systemData);

    SystemData getByKey(@Param("type") String type,@Param("key") String key);
}
