package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.TrackMapper;
import com.yibo.parking.entity.car.Track;
import com.yibo.parking.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackMapper trackMapper;

    @Override
    public List<Track> findList(Track track) {
        return trackMapper.findList(track);
    }

    @Override
    public Track get(Track track) {
        return null;
    }

    @Override
    public int insert(Track track) {
        return 0;
    }

    @Override
    public int update(Track track) {
        return 0;
    }

    @Override
    public int delete(Track track) {
        return 0;
    }
}
