package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.CarMapper;
import com.yibo.parking.dao.car.DispatchMapper;
import com.yibo.parking.dao.unit.UnitMapper;
import com.yibo.parking.entity.car.Car;
import com.yibo.parking.entity.car.Dispatch;
import com.yibo.parking.entity.unit.Unit;
import com.yibo.parking.service.DispatchService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DispatchServiceImpl implements DispatchService {

    @Autowired
    private DispatchMapper dispatchMapper;

    @Autowired
    private UnitMapper unitMapper;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Dispatch> findList(Dispatch dispatch) {
        return dispatchMapper.findList(dispatch);
    }

    @Override
    public Dispatch get(Dispatch dispatch) {
        return dispatchMapper.get(dispatch);
    }

    @Override
    public Map<String, Object> save(Dispatch dispatch) {
        Map<String,Object> map = new HashMap<>();
        List<Dispatch> find = dispatchMapper.findByCarId(dispatch);
        if (find.size() != 0) {
            map.put("flag",0);
            map.put("message","该车辆已分派请勿重复操作");
            return map;
        }
        if (dispatch.getId() != null) {
            map.put("flag",dispatchMapper.update(dispatch));
            map.put("message","车辆分派修改成功！");
        }else {
            Car car = carMapper.get(dispatch.getCar().getId());
            car.setDisFlag("1");
            carMapper.update(car);
            dispatch.setId(EntityIdGenerate.generateId());
            dispatch.setOprateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            map.put("flag",dispatchMapper.insert(dispatch));
            map.put("message","车辆分派成功!");
        }
        return map;
    }

    @Override
    public Map<String, Object> delete(Dispatch dispatch) {
        Map<String, Object> map = new HashMap<>();
        int flag = 0;
        dispatch = dispatchMapper.get(dispatch);
        if (dispatch != null){
            flag = dispatchMapper.delete(dispatch);
            map.put("flag", flag);
            map.put("message", "删除成功");
        }else {
            map.put("flag", flag);
            map.put("message", "信息有误，请联系管理员");
        }
        return map;
    }

    public List<Unit> findUnitList() {
        return unitMapper.getUnits();
    }

    public List<Car> findCars() {
        return carMapper.getAll();
    }

    public Car getCar(Car car){
        return carMapper.get(car.getId());
    }

    public Unit findUnit(Unit unit) {
        return unitMapper.get(unit.getId());
    }

    public Map<String,Object> saveRedis(String[] ids) {
        Map<String,Object> map = new HashMap<>();
        int i = 0;
        for (String s : ids) {
            String key = "car" + i;
            redisTemplate.opsForValue().set(key,s);
            i++;
        }
        map.put("length",i);
        map.put("message", "加入缓存成功");
        return map;
    }

    public Map<String, Object> saves(Integer length, Dispatch dispatch) {
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            String key = "car" + i;
            ids.add(String.valueOf(redisTemplate.opsForValue().get(key)));
        }
        Map<String, Object> map = new HashMap<>();
        for (String i : ids) {
            dispatch.setId(EntityIdGenerate.generateId());
            Car car = carMapper.get(i);
            dispatch.setCar(car);
            dispatch.setOprateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            dispatchMapper.insert(dispatch);
        }
        map.put("flag",1);
        map.put("message", "一键分派成功");
        return map;
    }
}
