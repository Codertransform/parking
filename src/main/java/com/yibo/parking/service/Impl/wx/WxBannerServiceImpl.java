package com.yibo.parking.service.Impl.wx;

import com.alibaba.fastjson.JSONArray;
import com.yibo.parking.dao.wx.WxBannerMapper;
import com.yibo.parking.entity.wx.Banner;
import com.yibo.parking.service.WxBannerService;
import com.yibo.parking.utils.EntityIdGenerate;
import com.yibo.parking.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        return bannerMapper.get(banner);
    }

    @Override
    public int save(Banner banner) {
        int s = 0;
        if (banner.getId() != null && !banner.getId().equals("")){
            s = bannerMapper.update(banner);
        }else {
            banner.setId(EntityIdGenerate.generateId());
            banner.setStatus("0");
            s= bannerMapper.insert(banner);
        }
        return s;
    }

    @Override
    public int delete(Banner banner) {
        return bannerMapper.delete(banner);
    }

    public int dels(String[] ids){
        int d = 0;
        for (String id : ids) {
            Banner b = new Banner();
            b.setId(id);
            bannerMapper.delete(b);
            d++;
        }
        return d;
    }

    public int changStatus(Banner banner){
        return bannerMapper.status(banner);
    }

    public String getBannersApi() {
        Banner banner = new Banner();
        banner.setStatus("0");
        List<Banner> banners = bannerMapper.getBanners(banner);;
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (Banner b : banners) {
            Map<String, Object> map = new HashMap<>();
            map.put("id",b.getId());
            map.put("name",b.getName());
            map.put("picAddress","https://wzytest.com" + b.getPicAddress());
            map.put("status",b.getStatus());
            map.put("remarks",b.getRemarks());
            mapList.add(map);
        }
        return JSONArray.toJSONString(mapList);
    }

    public Map<String,String> upload(MultipartFile picture) {
        return UploadUtil.upload(picture,"wx/banner");
    }
}
