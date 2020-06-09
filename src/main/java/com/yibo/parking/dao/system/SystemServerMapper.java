package com.yibo.parking.dao.system;

import com.yibo.parking.entity.system.SystemServer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemServerMapper {

    List<SystemServer> findList(SystemServer systemServer);

    int insert(SystemServer systemServer);
}
