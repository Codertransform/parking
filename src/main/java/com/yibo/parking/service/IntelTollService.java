package com.yibo.parking.service;

import com.yibo.parking.entity.work.IntelToll;

import java.util.List;
import java.util.Map;

public interface IntelTollService {

    List<IntelToll> findList(IntelToll intelToll);

    IntelToll get(IntelToll intelToll);

    Map<String, Object> save(IntelToll intelToll);

    Map<String, Object> delete(IntelToll intelToll);
}
