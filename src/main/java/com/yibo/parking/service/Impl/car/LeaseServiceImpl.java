package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.CarMapper;
import com.yibo.parking.dao.car.LeaseMapper;
import com.yibo.parking.dao.car.TypeInfoMapper;
import com.yibo.parking.dao.car.TypeMapper;
import com.yibo.parking.dao.member.MemberDao;
import com.yibo.parking.entity.car.Car;
import com.yibo.parking.entity.car.Lease;
import com.yibo.parking.entity.car.Type;
import com.yibo.parking.entity.member.Member;
import com.yibo.parking.service.LeaseService;
import com.yibo.parking.utils.EntityIdGenerate;
import com.yibo.parking.utils.OrderIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeaseServiceImpl implements LeaseService {

    @Autowired
    private LeaseMapper leaseMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private TypeInfoMapper infoMapper;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private MemberDao memberDao;

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
    public Map<String, Object> delete(String id) {
        Map<String,Object> map = new HashMap<>();
        int d = leaseMapper.delete(id);
        if (d != 0) {
            map.put("flag", true);
            map.put("message", "租用订单删除成功！");
        }else {
            map.put("flag", false);
            map.put("message", "信息有误请联系管理员！");
        }
        return map;
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

    public List<Type> getLeaseType(){
        return typeMapper.getTypes();
    }

    public Map<String,Object> saveApi(String carId, String typeCheck, String start, String end, String userId) {
        Map<String,Object> map = new HashMap<>();
        Lease lease = new Lease();
        Car car = carMapper.get(carId);
        lease.setId(EntityIdGenerate.generateId());
        lease.setOrderId(EntityIdGenerate.generateOrderId());
        lease.setCar(car);
        Member m = new Member();
        m.setId(userId);
        m = memberDao.get(m);
        lease.setUnit(m.getUnit());
        lease.setMember(m);
        Type type = typeMapper.get(car.getTypeId());
        lease.setType(type);
        lease.setAmount(String.valueOf(infoMapper.get(typeCheck).getValue()));
        lease.setStatus("0");
        lease.setStartdate(start);
        lease.setEnddate(end);
        map.put("flag",leaseMapper.insert(lease));
        map.put("lease",lease);
        return map;
    }

    public Map<String, Object> getOrders(String userId) {

        return null;
    }

    public Map<String, Object> check(Lease lease) {
        Map<String,Object> map = new HashMap<>();
        int l = leaseMapper.check(lease);
        Lease lease1 = leaseMapper.get(lease);
        map.put("flag",l);
        map.put("lease",lease1);
        switch (lease.getStatus()){
            case "2":
                map.put("msg","审核已通过，请提醒用车单位尽快支付款项");
                break;
            case "-2":
                map.put("msg","很抱歉，没能通过审核！");
                break;
            default:
                map.put("msg","订单已支付完成");
        }
        return map;
    }
}
