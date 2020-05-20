package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.Lease;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaseMapper {

    public Lease get(Lease lease);

    public List<Lease> getLeases(@Param("logmin")String logmin, @Param("logmax")String logmax, @Param("unit") String unit, @Param("car")String carId);

    public int insert(Lease lease);

    public int update(Lease lease);

    public int delete(String id);

    int check(Lease lease);
}
