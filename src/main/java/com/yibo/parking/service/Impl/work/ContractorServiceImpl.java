package com.yibo.parking.service.Impl.work;

import com.yibo.parking.dao.work.ContractorMapper;
import com.yibo.parking.entity.work.Contractor;
import com.yibo.parking.service.ContractorService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContractorServiceImpl implements ContractorService {

    @Autowired
    private ContractorMapper contractorMapper;

    @Override
    public List<Contractor> findList(Contractor contractor) {
        return contractorMapper.findList(contractor);
    }

    @Override
    public Contractor get(Contractor contractor) {
        return contractorMapper.get(contractor);
    }

    @Override
    public Map<String, Object> save(Contractor contractor) {
        Map<String, Object> map = new HashMap<>();
        if (contractor.getId() != null) {
            map.put("flag", contractorMapper.update(contractor));
            map.put("message", "承包人信息修改成功！");
            return map;
        }
        contractor.setId(EntityIdGenerate.generateId());
        map.put("flag", contractorMapper.insert(contractor));
        map.put("message", "承包人信息添加成功！");
        return map;
    }

    @Override
    public int delete(Contractor contractor) {
        return contractorMapper.delete(contractor);
    }

    public int dels(String[] ids) {
        int d = 0;
        for (String id : ids){
            Contractor contractor = new Contractor();
            contractor.setId(id);
            contractorMapper.delete(contractor);
            d++;
        }
        return d;
    }

    public List<Contractor> findAllList() {
        return contractorMapper.findList(new Contractor());
    }
}
