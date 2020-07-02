package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.Gallery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GalleryMapper {

    List<Gallery> findList(Gallery gallery);

    Gallery get(Gallery gallery);

    void insert(Gallery gallery);

    int update(Gallery gallery);

    int delete(Gallery gallery);

}
