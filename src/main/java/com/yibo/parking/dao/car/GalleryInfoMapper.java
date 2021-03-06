package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.GalleryInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GalleryInfoMapper {

    List<GalleryInfo> findList(GalleryInfo info);

    GalleryInfo get(GalleryInfo info);

    int insert(GalleryInfo info);

    int update(GalleryInfo info);

    int delete(GalleryInfo info);

    List<GalleryInfo> findInfosByCarId(@Param("carId") String carId);
}
