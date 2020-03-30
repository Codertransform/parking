package com.yibo.parking.service;

import com.yibo.parking.entity.unit.Unit;

import java.util.List;

public interface UnitService {
    Unit get(String id);

    List<Unit> getUnits();

    List<Unit> getUnitsBy(Unit unit);

    int save(Unit unit);

    int delete(String id);
}
