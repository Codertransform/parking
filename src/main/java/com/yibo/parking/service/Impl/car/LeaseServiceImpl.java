package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.CarMapper;
import com.yibo.parking.dao.car.LeaseMapper;
import com.yibo.parking.dao.car.TypeInfoMapper;
import com.yibo.parking.dao.car.TypeMapper;
import com.yibo.parking.dao.member.MemberDao;
import com.yibo.parking.dao.member.MemberWxInfoDao;
import com.yibo.parking.entity.car.Car;
import com.yibo.parking.entity.car.Lease;
import com.yibo.parking.entity.car.Type;
import com.yibo.parking.entity.car.TypeInfo;
import com.yibo.parking.entity.member.Member;
import com.yibo.parking.entity.member.MemberWxInfo;
import com.yibo.parking.service.LeaseService;
import com.yibo.parking.utils.EntityIdGenerate;
import com.yibo.parking.utils.OrderIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private MemberWxInfoDao infoDao;

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
        map.put("flag", true);
        List<Lease> le = leaseMapper.findByCarId(carId);
        if (le.size() == 0){
            Car car = carMapper.get(carId);
            Member member = new Member();
            member.setId(userId);
            member = memberDao.get(member);
            Type type = new Type();
            type.setId(car.getTypeId());
            TypeInfo typeInfo = new TypeInfo();
            typeInfo.setId(typeCheck);
            TypeInfo info = infoMapper.get(typeInfo);
            Lease lease = new Lease();
            lease.setId(EntityIdGenerate.generateId());
            lease.setOrderId(EntityIdGenerate.generateOrderId());
            lease.setCar(car);
            lease.setMember(member);
            lease.setUnit(member.getUnit());
            lease.setType(type);
            lease.setAmount(info.getValue().toString());
            lease.setStatus("0");
            lease.setStartdate(start);
            lease.setEnddate(end);
            int i = leaseMapper.insert(lease);
            if (i != 0) {
                map.put("code", "1002");
                map.put("lease",lease);
                map.put("message", "预约下单成功");
            }
        }else {
            System.out.println("123");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Lease l : le) {
                try {
                    Date start1 = format.parse(l.getStartdate());
                    Date end1 = format.parse(l.getEnddate());
                    Date startdate = format.parse(start + ":00");
                    Date enddate = format.parse(end + ":00");
                    System.out.println(startdate.after(start1));
                    System.out.println(enddate.after(end1));
                    if (startdate.after(start1) || enddate.before(end1)){
                        System.out.println("车辆已出租请选择其他车辆");
                        map.put("flag",false);
                        map.put("code", "1000");
                        map.put("message", "车辆已出租请选择其他车辆");
                    }else {
                        Car car = carMapper.get(carId);
                        Member member = new Member();
                        member.setId(userId);
                        member = memberDao.get(member);
                        Type type = new Type();
                        type.setId(car.getTypeId());
                        TypeInfo typeInfo = new TypeInfo();
                        typeInfo.setId(typeCheck);
                        TypeInfo info = infoMapper.get(typeInfo);
                        Lease lease = new Lease();
                        lease.setId(EntityIdGenerate.generateId());
                        lease.setOrderId(EntityIdGenerate.generateOrderId());
                        lease.setCar(car);
                        lease.setMember(member);
                        lease.setUnit(member.getUnit());
                        lease.setType(type);
                        lease.setAmount(info.getValue().toString());
                        lease.setStatus("0");
                        lease.setStartdate(start);
                        lease.setEnddate(end);
                        leaseMapper.insert(lease);
                        map.put("code", "1002");
                        map.put("lease",lease);
                        map.put("message", "预约下单成功");
                    }
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return map;
    }

    public Map<String, Object> getOrders(String userId) {
        Map<String, Object> map = new HashMap<>();
        MemberWxInfo info = new MemberWxInfo();
        info.setId(userId);
        MemberWxInfo in = infoDao.get(info);
        List<Lease> leases = leaseMapper.findByMemberId(in.getMember().getId());
        if (leases.size() != 0) {
            int all = leases.size();
            int unpay = 0;
            int paied = 0;
            for (Lease l : leases) {
                if (l.getStatus().equals("1")){
                    paied ++;
                }else {
                    unpay++;
                }
            }
            map.put("flag", true);
            map.put("message","查到所有订单");
            map.put("leases", leases);
            map.put("all",all);
            map.put("unpay",unpay);
            map.put("paied",paied);
        }else {
            map.put("flag", false);
            map.put("message", "未查到数据");
            map.put("leases", null);
        }
        return map;
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
                map.put("msg","订单支付已完成");
        }
        return map;
    }

    public Lease get(Lease lease) {
        return leaseMapper.get(lease);
    }

    public List<Lease> getOrdersAPI() {
        return null;
    }
}
