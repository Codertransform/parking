package com.yibo.parking.service;

import com.alibaba.fastjson.JSONArray;
import com.yibo.parking.entity.car.Track;

import java.util.List;

public interface TrackService {

    List<Track> findList(Track track);

    JSONArray get(Track track);

    int insert(Track track);

    int update(Track track);

    int delete(Track track);
}
