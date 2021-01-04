package com.yibo.parking.dao.device;

import com.yibo.parking.entity.device.Heart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HeartMapper {

    List<Heart> findList(Heart heart);

    Heart get(Heart heart);

    int insert(Heart heart);

    int update(Heart heart);

    int delete(Heart heart);
}
