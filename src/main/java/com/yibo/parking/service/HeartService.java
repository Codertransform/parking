package com.yibo.parking.service;

import com.yibo.parking.entity.device.Heart;

import java.util.List;

public interface HeartService {

    List<Heart> findList(Heart heart);

    Heart get(Heart heart);

    int insert(Heart heart);

    int update(Heart heart);

    int delete(Heart heart);
}
