package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.Track;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrackMapper {

    List<Track> findList(Track track);

    Track get(Track track);

    int insert(Track track);

    int update(Track track);

    int delete(Track track);
}
