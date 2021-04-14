package com.yibo.parking.service.Impl.work;

import com.yibo.parking.dao.work.IntelTollMapper;
import com.yibo.parking.entity.work.IntelToll;
import com.yibo.parking.service.IntelTollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IntelTollServiceImpl implements IntelTollService {

    @Autowired
    private IntelTollMapper intelTollMapper;

    @Override
    public List<IntelToll> findList(IntelToll intelToll) {
        return intelTollMapper.findList(intelToll);
    }

    @Override
    public IntelToll get(IntelToll intelToll) {
        return intelTollMapper.get(intelToll);
    }

    @Override
    public Map<String, Object> save(IntelToll intelToll) {
        return null;
    }

    @Override
    public Map<String, Object> delete(IntelToll intelToll) {
        return null;
    }
}
