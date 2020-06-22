package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.CarMapper;
import com.yibo.parking.dao.car.GalleryMapper;
import com.yibo.parking.entity.car.Car;
import com.yibo.parking.entity.car.Gallery;
import com.yibo.parking.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryMapper galleryMapper;

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Gallery> findList(Gallery gallery) {
        return galleryMapper.findList(gallery);
    }

    @Override
    public Gallery get(Gallery gallery) {
        return null;
    }

    @Override
    public void insert(Gallery gallery) {

    }

    @Override
    public void update(Gallery gallery) {

    }

    @Override
    public void delete(Gallery gallery) {

    }

    public void autoAdd() {
        List<Car> cars = carMapper.getByStatus();
        if (cars.size() == 0) {

        }
        for (Car c : cars) {

        }
    }
}
