package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.CarMapper;
import com.yibo.parking.dao.car.TrackFoMapper;
import com.yibo.parking.dao.car.TrackMapper;
import com.yibo.parking.entity.car.Car;
import com.yibo.parking.entity.car.Track;
import com.yibo.parking.entity.car.TrackFo;
import com.yibo.parking.service.TrackFoService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrackFoServiceImpl implements TrackFoService {

    @Autowired
    private TrackFoMapper trackFoMapper;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private TrackMapper trackMapper;

    @Override
    public List<TrackFo> findList(TrackFo trackFo) {
        return trackFoMapper.findList(trackFo);
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
    public Map<String, Object> save(TrackFo trackFo) {
        System.out.println(trackFo);
        Map<String, Object> map = new HashMap<>();
        trackFo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if (trackFo.getId() != null){
            map.put("flag",trackFoMapper.update(trackFo));
            map.put("message","更新成功");
        }else {
            trackFo.setId(EntityIdGenerate.generateId());
            Car car = carMapper.get(trackFo.getCarId());
            trackFo.setCardId(car.getCardId());
            Car tar = carMapper.get(trackFo.getTargetId());
            trackFo.setTargetCardId(tar.getCardId());
            Track t = trackMapper.findByCarId(tar.getCardId());
            trackFo.setTrackId(t.getTrackId());
            map.put("flag",trackFoMapper.insert(trackFo));
            map.put("message","添加成功");
        }
        return map;
    }

    @Override
    public int delete(TrackFo trackFo) {
        return trackFoMapper.delete(trackFo);
    }
}
