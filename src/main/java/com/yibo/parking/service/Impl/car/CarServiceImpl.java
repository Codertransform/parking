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
        List<Car> cars = carMapper.getCars();
        for (Car car : cars) {
            switch (car.getCarType()){
                case "0":
                    car.setCarType("SUV");
                    break;
                default:
                    car.setCarType("轿车");
                    break;
            }
            switch (car.getStatus()){
                case "0":
                    car.setStatus("正常");
                    break;
                case "1":
                    car.setStatus("待保养");
                    break;
                case "2":
                    car.setStatus("待维修");
                    break;
                case "3":
                    car.setStatus("维修中");
                    break;
                default:
                    car.setStatus("已停用");
                    break;
            }
        }
        return cars;
    }

    @Override
    public int save(Car car) {
        if (car.getId() != null){
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
}
