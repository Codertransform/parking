package com.yibo.parking.service;

import com.yibo.parking.entity.car.Lease;

import java.util.List;
import java.util.Map;

public interface LeaseService {

    public List<Lease> getLeases(String logmin, String logmax, String unit, String carId);

    public int save(Lease lease);

    public Map<String, Object> delete(String id);

    public int dels(String[] ids);
}
