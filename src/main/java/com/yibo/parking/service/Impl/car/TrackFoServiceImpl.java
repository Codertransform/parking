package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.TrackFoMapper;
import com.yibo.parking.entity.car.TrackFo;
import com.yibo.parking.service.TrackFoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackFoServiceImpl implements TrackFoService {

    @Autowired
    private TrackFoMapper trackFoMapper;

    @Override
    public List<TrackFo> findList(TrackFo trackFo) {
        return null;
    }

    @Override
    public TrackFo get(TrackFo trackFo) {
        return trackFoMapper.get(trackFo);
    }

    @Override
    public int insert(TrackFo trackFo) {
        return 0;
    }

    @Override
    public int update(TrackFo trackFo) {
        return 0;
    }

    @Override
    public int delete(TrackFo trackFo) {
        return 0;
    }
}
