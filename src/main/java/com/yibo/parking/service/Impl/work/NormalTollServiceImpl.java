package com.yibo.parking.service.Impl.work;

import com.yibo.parking.dao.work.ContractorTollMapper;
import com.yibo.parking.dao.work.NormalTollMapper;
import com.yibo.parking.entity.work.ContractorToll;
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

    @Autowired
    private ContractorTollMapper contractorTollMapper;

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

    public Map<String, Object> bindSave(ContractorToll contractorToll) {
        List<ContractorToll> tolls = contractorTollMapper.findList(contractorToll);
        Map<String, Object> map = new HashMap<>();
        if (tolls.size() != 0){
            map.put("flag",0);
            map.put("message", "已绑定承包人，请勿重复操作");
        }else{
            if (contractorToll.getId() != null){
                map.put("flag", contractorTollMapper.update(contractorToll));
                map.put("message", "更新成功");
            }else {
                contractorToll.setId(EntityIdGenerate.generateId());
                map.put("flag", contractorTollMapper.insert(contractorToll));
                map.put("message", "绑定成功");
            }
        }
        return map;
    }
}
