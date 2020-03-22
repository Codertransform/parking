package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.LeaseMapper;
import com.yibo.parking.dao.car.TypeMapper;
import com.yibo.parking.entity.car.Lease;
import com.yibo.parking.entity.car.Type;
import com.yibo.parking.service.LeaseService;
import com.yibo.parking.utils.EntityIdGenerate;
import com.yibo.parking.utils.OrderIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeaseServiceImpl implements LeaseService {

    @Autowired
    private LeaseMapper leaseMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Lease> getLeases(String logmin, String logmax, String unit, String carId) {
        return leaseMapper.getLeases(logmin,logmax,unit,carId);
    }

    @Override
    public int save(Lease lease) {
        if (lease.getId() != null && !lease.getId().isEmpty()){
            return leaseMapper.update(lease);
        }else {
            lease.setId(EntityIdGenerate.generateId());
            lease.setOrderId(OrderIdGenerate.generateOrderId());
            return leaseMapper.insert(lease);
        }
    }

    @Override
    public int delete(String id) {
        return leaseMapper.delete(id);
    }

    @Override
    public int dels(String[] ids) {
        int d = 0;
        for (String id : ids) {
            leaseMapper.delete(id);
            d++;
        }
        return d;
    }

    public List<Type> getTypes() {
        return typeMapper.getTypes();
    }

    public Map<String,Integer> getLeaseType(){
        List<Type> types = typeMapper.getTypes();
        Map<String,Integer> map = new LinkedHashMap<>();
        for (Type t : types) {
            map.put("小时",t.getHour());
            map.put("半天",t.getHalfday());
            map.put("全天",t.getAllday());
            map.put("周",t.getWeek());
            map.put("月",t.getMonth());
            map.put("半年",t.getHalfyear());
        }
        return map;
    }
}
