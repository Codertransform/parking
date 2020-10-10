package com.yibo.parking.service;

import com.yibo.parking.entity.car.TrackFo;

import java.util.List;

public interface TrackFoService {

    List<TrackFo> findList(TrackFo trackFo);

    TrackFo get(TrackFo trackFo);

    int insert(TrackFo trackFo);

    int update(TrackFo trackFo);

    int delete(TrackFo trackFo);
}
