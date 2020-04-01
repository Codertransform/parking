package com.yibo.parking.service;

import com.yibo.parking.entity.wx.Banner;

import java.util.List;

public interface WxBannerService {

    List<Banner> getBanners(Banner banner);

    Banner get(Banner banner);

    int save(Banner banner);

    int delete(Banner banner);
}
