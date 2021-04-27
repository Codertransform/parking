package com.yibo.parking.service.Impl.work;

import com.yibo.parking.dao.system.SystemDataMapper;
import com.yibo.parking.dao.work.IntelTollMapper;
import com.yibo.parking.entity.system.SystemData;
import com.yibo.parking.entity.work.IntelToll;
import com.yibo.parking.service.IntelTollService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IntelTollServiceImpl implements IntelTollService {

    @Autowired
    private IntelTollMapper intelTollMapper;

    @Autowired
    private SystemDataMapper systemDataMapper;

    @Override
    public List<IntelToll> findList(IntelToll intelToll) {
        List<IntelToll> list = intelTollMapper.findList(intelToll);
        for (IntelToll toll : list) {
            if (toll.getSupplies() != null && !toll.getSupplies().equals("")){
                String[] s = toll.getSupplies().split(",");
                System.out.println(Arrays.toString(s));
            }
        }
        return list;
    }

    @Override
    public IntelToll get(IntelToll intelToll) {
        return intelTollMapper.get(intelToll);
    }

    @Override
    public Map<String, Object> save(IntelToll intelToll) {
        Map<String, Object> map = new HashMap<>();
        if (intelToll.getId() != null) {
            int u = intelTollMapper.update(intelToll);
            map.put("flag",u);
            if (u != 0) {
                map.put("message","修改成功");
            }else {
                map.put("message", "修改失败");
            }
        }else {
            intelToll.setId(EntityIdGenerate.generateId());
            int i = intelTollMapper.insert(intelToll);
            map.put("flag",i);
            if (i != 0) {
                map.put("message","添加成功");
            }else {
                map.put("message", "添加失败");
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> delete(IntelToll intelToll) {
        return null;
    }

    public List<SystemData> getDatas(){
        SystemData data = new SystemData();
        data.setType("supplies");
        return systemDataMapper.findList(data);
    }
}
