package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.DispatchMapper;
import com.yibo.parking.entity.car.Dispatch;
import com.yibo.parking.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispatchServiceImpl implements DispatchService {

    @Autowired
    private DispatchMapper dispatchMapper;

    @Override
    public List<Dispatch> findList(Dispatch dispatch) {
        return dispatchMapper.findList(dispatch);
    }

    @Override
    public Dispatch get(Dispatch dispatch) {
        return dispatchMapper.get(dispatch);
    }

    @Override
    public int save(Dispatch dispatch) {
        return 0;
    }

    @Override
    public int delete(Dispatch dispatch) {
        return 0;
    }
}
