package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.TrackFo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrackFoMapper {

    List<TrackFo> findList(TrackFo trackFo);

    TrackFo get(TrackFo trackFo);

    int insert(TrackFo trackFo);

    int update(TrackFo trackFo);

    int delete(TrackFo trackFo);
}
