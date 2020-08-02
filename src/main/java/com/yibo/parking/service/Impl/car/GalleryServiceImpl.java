package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.CarMapper;
import com.yibo.parking.dao.car.GalleryInfoMapper;
import com.yibo.parking.dao.car.GalleryMapper;
import com.yibo.parking.entity.car.Car;
import com.yibo.parking.entity.car.Gallery;
import com.yibo.parking.entity.car.GalleryInfo;
import com.yibo.parking.service.GalleryService;
import com.yibo.parking.utils.EntityIdGenerate;
import com.yibo.parking.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryMapper galleryMapper;

    @Autowired
    private GalleryInfoMapper infoMapper;

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Gallery> findList(Gallery gallery) {
        return galleryMapper.findList(gallery);
    }

    @Override
    public Gallery get(Gallery gallery) {
        return galleryMapper.get(gallery);
    }

    @Override
    public void insert(Gallery gallery) {

    }

    @Override
    public int update(Gallery gallery) {
        gallery.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return galleryMapper.update(gallery);
    }

    @Override
    public int delete(Gallery gallery) {
        gallery = galleryMapper.get(gallery);
        Car car = gallery.getCar();
        car.setPicStatus("0");
        carMapper.updatePicStatus(car);
        return galleryMapper.delete(gallery);
    }

    public Map<String,Object> autoCreate() {
        Map<String,Object> map = new HashMap<>();
        Car car = new Car();
        car.setPicStatus("0");
        List<Car> cars = carMapper.findByPicStatus(car);
        if (cars.size() == 0) {
            map.put("flag", false);
            map.put("code", "1001");
            map.put("message", "未检测到有效车辆，请先添加车辆");
            map.put("data", null);
        }else {
            List<Gallery> galleries = new ArrayList<>();
            for (Car c : cars) {
                Gallery gallery = new Gallery();
                gallery.setId(EntityIdGenerate.generateId());
                gallery.setCar(c);
                gallery.setMaxSize("5");
                gallery.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                gallery.setStatus("0");
                galleryMapper.insert(gallery);
                galleries.add(gallery);
                c.setPicStatus("1");
                carMapper.update(c);
            }
            map.put("flag", true);
            map.put("code","1002");
            map.put("data", galleries);
            map.put("message", "所有车辆相册创建成功");
        }
        return map;
    }

    public Map<String, String> upload(MultipartFile picture) {
        return UploadUtil.upload(picture,"cars/thumb");
    }

    public Map<String, String> uploads(MultipartFile picture, String picId,String carName) {
        Map<String, String> map = UploadUtil.upload(picture,"cars/img/"+carName);
        GalleryInfo info = new GalleryInfo();
        info.setId(EntityIdGenerate.generateId());
        info.setPicId(picId);
        info.setImgUrl(map.get("src"));
        info.setTitle(carName);
        info.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        infoMapper.insert(info);
        return map;
    }

    public String getName(Gallery gallery) throws Exception {
        gallery = galleryMapper.get(gallery);
        return gallery.getCar().getCardId();
    }

    public List<GalleryInfo> findInfos(Gallery gallery) {
        GalleryInfo info = new GalleryInfo();
        info.setPicId(gallery.getId());
        return infoMapper.findList(info);
    }

    public List<GalleryInfo> findInfosByCarId(String carId){
        List<GalleryInfo> infos = infoMapper.findInfosByCarId(carId);
        for (GalleryInfo info: infos) {
            info.setImgUrl("https://wzytest.com" + info.getImgUrl());
        }
        return infos;
    }
}
