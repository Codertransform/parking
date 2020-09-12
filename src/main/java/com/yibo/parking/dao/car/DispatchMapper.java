package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.Dispatch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DispatchMapper {

    List<Dispatch> findList(Dispatch dispatch);

    Dispatch get(Dispatch dispatch);

    int insert(Dispatch dispatch);

    int update(Dispatch dispatch);

    int delete(Dispatch dispatch);

    List<Dispatch> findByCarId(Dispatch dispatch);
}
