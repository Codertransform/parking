package com.yibo.parking.service.Impl.unit;

import com.yibo.parking.dao.unit.UnitMapper;
import com.yibo.parking.entity.unit.Unit;
import com.yibo.parking.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceIpml implements UnitService {

    @Autowired
    private UnitMapper unitMapper;

    @Override
    public List<Unit> getUnits() {
        return unitMapper.getUnits();
    }

    @Override
    public List<Unit> getUnitsBy(Unit unit) {
        return unitMapper.getUnitsBy(unit);
    }

    @Override
    public int save(Unit unit) {
        if (unit.getId() != null && !unit.getId().isEmpty())
            return unitMapper.update(unit);
        else
            return unitMapper.insert(unit);
    }

    @Override
    public int delete(String id) {
        return unitMapper.delete(id);
    }
}
