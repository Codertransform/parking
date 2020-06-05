package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.TransformDataMapper;
import com.yibo.parking.entity.car.TransformData;
import com.yibo.parking.service.TransformDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransformDataServiceImpl implements TransformDataService {

    @Autowired
    private TransformDataMapper dataMapper;

    @Override
    public List<Map<String,String>> findList(TransformData data) {
        List<TransformData> list = dataMapper.findList(data);
        List<Map<String,String>> mapList = new ArrayList<>();
        for (TransformData d : list) {
            Map<String,String> map = new HashMap<>();
            map.put("latitude", d.getLatitude());
            map.put("longitude", d.getLongitude());
            map.put("carId", d.getCarId());
            mapList.add(map);
        }
        return mapList;
    }
}
