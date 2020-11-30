package com.yibo.parking.service;

import com.yibo.parking.entity.work.NormalToll;

import java.util.List;
import java.util.Map;

public interface NormalTollService {

    List<NormalToll> findList(NormalToll normalToll);

    NormalToll get(NormalToll normalToll);

    Map<String, Object> save(NormalToll normalToll);

    Map<String, Object> delete(NormalToll normalToll);
}
