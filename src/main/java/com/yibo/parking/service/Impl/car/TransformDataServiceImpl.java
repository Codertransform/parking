package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.TransformDataMapper;
import com.yibo.parking.entity.car.TransformData;
import com.yibo.parking.service.TransformDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransformDataServiceImpl implements TransformDataService {

    @Autowired
    private TransformDataMapper dataMapper;

    @Override
    public List<TransformData> findList(TransformData data) {
        return dataMapper.findList(data);
    }
}
