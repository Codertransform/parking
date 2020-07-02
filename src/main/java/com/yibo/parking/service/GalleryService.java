package com.yibo.parking.service;

import com.yibo.parking.entity.car.Gallery;

import java.util.List;

public interface GalleryService {

    List<Gallery> findList(Gallery gallery);

    Gallery get(Gallery gallery);

    void insert(Gallery gallery);

    int update(Gallery gallery);

    int delete(Gallery gallery);
}
