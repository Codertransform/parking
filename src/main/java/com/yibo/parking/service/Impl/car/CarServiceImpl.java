package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.CarMapper;
import com.yibo.parking.entity.car.Car;
import com.yibo.parking.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> getCars() {
        return carMapper.getCars();
    }

    @Override
    public int save(Car car) {
        if (car.getId().isEmpty()){
            return carMapper.update(car);
        }else {
            car.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            return carMapper.insert(car);
        }
    }

    public int del(String id) {
        return carMapper.del(id);
    }

    public Car get(String id) {
        return carMapper.get(id);
    }
}
