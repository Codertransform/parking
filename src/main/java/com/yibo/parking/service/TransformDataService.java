package com.yibo.parking.service;

import com.yibo.parking.entity.car.TransformData;

import java.util.List;
import java.util.Map;

public interface TransformDataService {

    List<Map<String,String>> findList(TransformData data);
}
