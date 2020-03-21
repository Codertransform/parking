package com.yibo.parking.service;

import com.yibo.parking.entity.car.Lease;

import java.util.List;

public interface LeaseService {

    public List<Lease> getLeases(Lease lease);

    public int save(Lease lease);

    public int delete(String id);

    public int dels(String[] ids);
}
