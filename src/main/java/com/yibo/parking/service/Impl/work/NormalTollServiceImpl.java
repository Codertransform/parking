package com.yibo.parking.service.Impl.work;

import com.yibo.parking.dao.work.NormalTollMapper;
import com.yibo.parking.entity.work.NormalToll;
import com.yibo.parking.service.NormalTollService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NormalTollServiceImpl implements NormalTollService {

    @Autowired
    private NormalTollMapper normalTollMapper;

    @Override
    public List<NormalToll> findList(NormalToll normalToll) {
        return normalTollMapper.findList(normalToll);
    }

    @Override
    public NormalToll get(NormalToll normalToll) {
        return normalTollMapper.get(normalToll);
    }

    @Override
    public Map<String, Object> save(NormalToll normalToll) {
        Map<String, Object> map = new HashMap<>();
        if (normalToll.getId() != null){
            map.put("flag", normalTollMapper.update(normalToll));
            map.put("message", "更新成功");
        }else {
            normalToll.setId(EntityIdGenerate.generateId());
            map.put("flag", normalTollMapper.insert(normalToll));
            map.put("message", "添加成功");
        }
        return map;
    }

    @Override
    public Map<String, Object> delete(NormalToll normalToll) {
        Map<String, Object> map = new HashMap<>();
        int d = normalTollMapper.delete(normalToll);
        if (d != 0){
            map.put("flag", d);
            map.put("message", "删除成功");
        }
        return map;
    }
}
