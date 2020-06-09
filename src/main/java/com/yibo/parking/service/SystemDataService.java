package com.yibo.parking.service;

import com.yibo.parking.entity.system.SystemData;

import java.util.List;

public interface SystemDataService {

    List<SystemData> findList(SystemData data);

    SystemData get(SystemData systemData);

    int save(SystemData systemData);

    int delete(SystemData systemData);
}
