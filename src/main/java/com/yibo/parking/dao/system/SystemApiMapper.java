package com.yibo.parking.dao.system;

import com.yibo.parking.entity.system.SystemApi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemApiMapper {

    List<SystemApi> findList(SystemApi systemApi);
}
