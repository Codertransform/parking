package com.yibo.parking.service.Impl.system;

import com.yibo.parking.entity.system.RentalStrategy;
import com.yibo.parking.service.RentalStrategyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalStrategyServiceImpl implements RentalStrategyService {



    @Override
    public List<RentalStrategy> findList(RentalStrategy rentalStrategy) {
        return null;
    }

    @Override
    public RentalStrategy get(RentalStrategy rentalStrategy) {
        return null;
    }

    @Override
    public int save(RentalStrategy rentalStrategy) {
        return 0;
    }

    @Override
    public int delete(RentalStrategy rentalStrategy) {
        return 0;
    }
}
