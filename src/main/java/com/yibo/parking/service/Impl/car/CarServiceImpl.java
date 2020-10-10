package com.yibo.parking.service.Impl.car;

import com.alibaba.fastjson.JSONArray;
import com.yibo.parking.dao.car.CarMapper;
import com.yibo.parking.dao.car.GalleryInfoMapper;
import com.yibo.parking.dao.car.GalleryMapper;
import com.yibo.parking.dao.car.LeaseMapper;
import com.yibo.parking.entity.car.Car;
import com.yibo.parking.entity.car.Gallery;
import com.yibo.parking.entity.car.Lease;
import com.yibo.parking.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private LeaseMapper leaseMapper;

    @Autowired
    private GalleryMapper galleryMapper;

    @Autowired
    private GalleryInfoMapper galleryInfoMapper;

    @Override
    public List<Car> getCars(String typeId, String logmin, String logmax, String cardId) {
        return carMapper.getCars(typeId,logmin,logmax,cardId);
    }

    public List<Car> getAllCars(){
        return carMapper.getAll();
    }

    @Override
    public int save(Car car) {
        if (car.getId() != null){
            return carMapper.update(car);
        }else {
            car.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            car.setStatus("0");
            car.setPicStatus("0");
            if (car.getMaintenance().equals("")){
                car.setMaintenance(null);
            }
            return carMapper.insert(car);
        }
    }

    public int del(String id) {
        return carMapper.del(id);
    }

    public Car get(String id) {
        return carMapper.get(id);
    }

    public int dels(String[] ids) {
        int d = 0;
        for(String id : ids){
            carMapper.del(id);
            d++;
        }
        return d;
    }

    public int stop(String id) {
        return carMapper.stop(id);
    }

    public int start(String id) {
        return carMapper.start(id);
    }

    @Override
    public String getByStatus() {
        List<Car> cars = carMapper.getByStatus();
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (Car c : cars) {
            List<Lease> leases = leaseMapper.findByCarId(c.getId());
            if (leases.size() == 0) {
                Map<String,Object> map = new HashMap<>();
                map.put("id",c.getId());
                map.put("cardId",c.getCardId());
                map.put("model", c.getModel());
                map.put("color", c.getColor());
                map.put("carType", c.getTypeName());
                map.put("status", c.getStatus());
                if (c.getPicStatus().equals("1")){
                    Gallery gallery = new Gallery();
                    gallery.setCar(c);
                    gallery = galleryMapper.get(gallery);
                    if (gallery.getThumb() != null){
                        map.put("thumb","https://wzytest.com"+gallery.getThumb());
                    }else {
                        map.put("thumb", "null");
                    }
                }
                map.put("buy_time", c.getBuy_time());
                map.put("maintenance", c.getMaintenance());
                mapList.add(map);
            }
        }
        return JSONArray.toJSONString(mapList);
    }

    public Car getByCardId(String cardId){
        Car car = new Car();
        car.setCardId(cardId);
        return carMapper.findByCardId(car);
    }
}
