package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.Lease;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeaseMapper {

    public Lease get(Lease lease);

    public List<Lease> getLeases(Lease lease);

    public int insert(Lease lease);

    public int update(Lease lease);

    public int delete(String id);
}
