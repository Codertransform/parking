package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.TransformDataMapper;
import com.yibo.parking.entity.car.TransformData;
import com.yibo.parking.service.TransformDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransformDataServiceImpl implements TransformDataService {

    @Autowired
    private TransformDataMapper dataMapper;

    @Override
    public List<String[]> findList(TransformData data) {
        List<TransformData> datas = dataMapper.findList(data);
        List<String[]> list = new ArrayList<>();
        for (TransformData d : datas) {
            String[] tds = {d.getCarId(),d.getLatitude(),d.getLongitude()};
            list.add(tds);
        }
        return list;
    }

    public List<TransformData> findList(){
        return dataMapper.findList(new TransformData());
    }

    public List<TransformData> getLocation() {
        return dataMapper.getLocation();
    }
}
