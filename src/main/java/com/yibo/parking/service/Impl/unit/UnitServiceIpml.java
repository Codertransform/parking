package com.yibo.parking.service.Impl.unit;

import com.alibaba.fastjson.JSONArray;
import com.yibo.parking.dao.unit.UnitMapper;
import com.yibo.parking.entity.unit.Unit;
import com.yibo.parking.service.UnitService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UnitServiceIpml implements UnitService {

    @Autowired
    private UnitMapper unitMapper;

    private final List<Unit> units = new ArrayList<>();

    @Override
    public List<Unit> getUnits() {
        return unitMapper.getUnits();
    }

    @Override
    public List<Unit> getUnitsBy(Unit unit) {
        return unitMapper.getUnitsBy(unit);
    }

    public List<Unit> getSonUnits(String parentId){
        units.clear();
        Unit unit = new Unit();
        unit.setParentId(parentId);
        List<Unit> unitList = unitMapper.getUnitsBy(unit);
        for (Unit u : unitList) {
            if (u.getParentId().equals(parentId)){
                units.add(u);
                getSonUnits(u.getId());
            }
        }
        return units;
    }

    @Override
    public int save(Unit unit) {
        if (unit.getParentId() == null){
            unit.setParentId("0");
        }
        if (unit.getId() != null && !unit.getId().isEmpty())
            return unitMapper.update(unit);
        else{
            unit.setId(EntityIdGenerate.generateId());
            return unitMapper.insert(unit);
        }
    }

    @Override
    public int delete(String id) {
        return unitMapper.delete(id);
    }

    public String List2Josn(List<Unit> unitsList) {
        List<Map<String,Object>> list = new ArrayList<>();
        for (Unit u : unitsList) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("id",u.getId());
            map.put("pId",u.getParentId());
            map.put("name",u.getName());
            if (u.getParentId().equals("0")){
                map.put("open",true);
            }
            list.add(map);
        }
        return JSONArray.toJSONString(list);
    }

    public Unit get(String id) {
        return unitMapper.get(id);
    }

    public List<Unit> findList(){
        return null;
    }
}
