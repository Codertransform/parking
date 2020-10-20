package com.yibo.parking.service;

import com.yibo.parking.entity.car.TrackFo;

import java.util.List;
import java.util.Map;

public interface TrackFoService {

    List<TrackFo> findList(TrackFo trackFo);

    TrackFo get(TrackFo trackFo);

    int insert(TrackFo trackFo);

    int update(TrackFo trackFo);

    Map<String,Object> save(TrackFo trackFo);

    int delete(TrackFo trackFo);
}
