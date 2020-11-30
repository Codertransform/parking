package com.yibo.parking.service;

import com.yibo.parking.entity.system.RentalStrategy;

import java.util.List;

public interface RentalStrategyService {

    public List<RentalStrategy> findList(RentalStrategy rentalStrategy);

    RentalStrategy get(RentalStrategy rentalStrategy);

    int save(RentalStrategy rentalStrategy);

    int delete(RentalStrategy rentalStrategy);
}
