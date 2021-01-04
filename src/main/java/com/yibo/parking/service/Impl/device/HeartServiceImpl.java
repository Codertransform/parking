package com.yibo.parking.service.Impl.device;

import com.yibo.parking.dao.device.HeartMapper;
import com.yibo.parking.entity.device.Heart;
import com.yibo.parking.service.HeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeartServiceImpl implements HeartService {

    @Autowired
    private HeartMapper heartMapper;

    @Override
    public List<Heart> findList(Heart heart) {
        return heartMapper.findList(heart);
    }

    @Override
    public Heart get(Heart heart) {
        return heartMapper.get(heart);
    }

    @Override
    public int insert(Heart heart) {
        return heartMapper.insert(heart);
    }

    @Override
    public int update(Heart heart) {
        return heartMapper.update(heart);
    }

    @Override
    public int delete(Heart heart) {
        return heartMapper.delete(heart);
    }
}
