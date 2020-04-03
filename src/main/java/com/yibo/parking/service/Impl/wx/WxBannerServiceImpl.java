package com.yibo.parking.service.Impl.wx;

import com.yibo.parking.dao.wx.WxBannerMapper;
import com.yibo.parking.entity.wx.Banner;
import com.yibo.parking.service.WxBannerService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxBannerServiceImpl implements WxBannerService {

    @Autowired
    private WxBannerMapper bannerMapper;

    @Override
    public List<Banner> getBanners(Banner banner) {
        return bannerMapper.getBanners(banner);
    }

    @Override
    public Banner get(Banner banner) {
        return null;
    }

    @Override
    public int save(Banner banner) {
        int s = 0;
        if (banner.getId() != null && !banner.getId().equals("")){
            s = bannerMapper.update(banner);
        }else {
            banner.setId(EntityIdGenerate.generateId());
            s= bannerMapper.insert(banner);
        }
        return s;
    }

    @Override
    public int delete(Banner banner) {
        return 0;
    }

    public List<Map<String,Object>> getBannersApi() {
        List<Banner> banners = bannerMapper.getBanners(new Banner());
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (Banner b : banners) {
            Map<String, Object> map = new HashMap<>();
            map.put("id",b.getId());
            map.put("name",b.getName());
            map.put("picAddress",b.getPicAddress());
            map.put("status",b.getStatus());
            map.put("remarks",b.getRemarks());
            mapList.add(map);
        }
        return mapList;
    }
}
