package com.yibo.parking.dao.unit;

import com.yibo.parking.entity.unit.Unit;
import com.yibo.parking.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UnitMapper {

    List<Unit> getUnits();

    List<Unit> getUnitsBy(Unit unit);

    int insert(Unit unit);

    int update(Unit unit);

    int delete(String id);

    Unit get(String id);

    Unit findByUser(User user);
}
