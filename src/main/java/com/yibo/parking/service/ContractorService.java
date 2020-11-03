package com.yibo.parking.service;

import com.yibo.parking.entity.work.Contractor;

import java.util.List;
import java.util.Map;

public interface ContractorService {

    List<Contractor> findList(Contractor contractor);

    Contractor get(Contractor contractor);

    Map<String, Object> save(Contractor contractor);

    int delete(Contractor contractor);
}
