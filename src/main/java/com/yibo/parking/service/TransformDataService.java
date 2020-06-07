package com.yibo.parking.service;

import com.yibo.parking.entity.car.TransformData;

import java.util.List;

public interface TransformDataService {

    List<String[]> findList(TransformData data);
}
