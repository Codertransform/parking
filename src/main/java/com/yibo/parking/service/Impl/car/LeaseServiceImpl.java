package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.LeaseMapper;
import com.yibo.parking.entity.car.Lease;
import com.yibo.parking.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseServiceImpl implements LeaseService {

    @Autowired
    private LeaseMapper leaseMapper;

    @Override
    public List<Lease> getLeases(Lease lease) {
        return leaseMapper.getLeases(lease);
    }

    @Override
    public int save(Lease lease) {
        if (lease.getId() != null && !lease.getId().isEmpty()){
            return leaseMapper.update(lease);
        }else {
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
}
