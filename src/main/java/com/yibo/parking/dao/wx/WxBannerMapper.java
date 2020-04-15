package com.yibo.parking.dao.wx;

import com.yibo.parking.entity.wx.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WxBannerMapper {

    List<Banner> getBanners(Banner banner);

    Banner get(Banner banner);

    int insert(Banner banner);

    int update(Banner banner);

    int delete(Banner banner);

    int status(Banner banner);
}
