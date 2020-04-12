package com.yibo.parking.service.Impl.wx;

import com.yibo.parking.dao.wx.WxBannerMapper;
import com.yibo.parking.entity.wx.Banner;
import com.yibo.parking.service.WxBannerService;
import com.yibo.parking.utils.EntityIdGenerate;
import com.yibo.parking.utils.mimeUtils;
import net.sf.jmimemagic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class WxBannerServiceImpl implements WxBannerService {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

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

    public String upload(MultipartFile picture) {
        String pictureName = "";
        String fileSavePath = "";
        try {
            //获取上传路径
            fileSavePath = uploadPath + "banner/";
            /**
             * transferTo在开发Web应用程序时比较常见的功能之一，
             * 就是允许用户利用multipart请求将本地文件上传到服务器,
             * Spring通过对ServletAPI的HttpServletRequest接口进行扩展，使其能够很好地处理文件上传
             */
            File file = new File(fileSavePath);
            if (!file.exists()){
                file.mkdir();
            }
            String mime = "";
            if (picture.getOriginalFilename() != null){
                File f = new File(picture.getOriginalFilename());
                MagicMatch match = Magic.getMagicMatch(f, false);
                mime = match.getMimeType();
            }
            //设置图片为唯一的id
            pictureName = EntityIdGenerate.generateImgName() + "." + mime;
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (IOException | MagicException | MagicParseException | MagicMatchNotFoundException e) {
            e.printStackTrace();
        }
        return fileSavePath + pictureName;
    }
}
