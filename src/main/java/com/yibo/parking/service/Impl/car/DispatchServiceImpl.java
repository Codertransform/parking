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
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DispatchServiceImpl implements DispatchService {

    @Autowired
    private DispatchMapper dispatchMapper;

    @Autowired
    private UnitMapper unitMapper;

    @Autowired
    private CarMapper carMapper;

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
        System.out.println(dispatch.getCar().getId());
        List<Dispatch> find = dispatchMapper.findByCarId(dispatch);
        if (find.size() != 0) {
            map.put("flag",0);
//            map.put("message","该车辆已分派请勿重复操作");
            return map;
        }
        if (dispatch.getId() != null) {
            map.put("flag",dispatchMapper.update(dispatch));
            map.put("message","车辆分派修改成功！");
        }else {
            dispatch.setId(EntityIdGenerate.generateId());
            dispatch.setOprateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            map.put("flag",dispatchMapper.insert(dispatch));
            map.put("message","车辆分派成功!");
        }
        return map;
    }

    @Override
    public int delete(Dispatch dispatch) {
        return 0;
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
}
