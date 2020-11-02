package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.work.MaintainOrderMapper;
import com.yibo.parking.entity.car.MaintainOrder;
import com.yibo.parking.service.MaintainOrderService;
import com.yibo.parking.utils.OrderIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MaintainOrderServiceImpl implements MaintainOrderService {

    @Autowired
    private MaintainOrderMapper maintainOrderMapper;

    @Override
    public List<MaintainOrder> getOrders() {
        return maintainOrderMapper.getOrders();
    }

    public int save(MaintainOrder order) {
        if (order.getId() != null){
            return maintainOrderMapper.update(order);
        }else {
            order.setId(UUID.randomUUID().toString().replaceAll("-",""));
            order.setOrder_id(OrderIdGenerate.generateOrderId());
            order.setStatus("-1");
            return maintainOrderMapper.insert(order);
        }
    }

    public int check(String id, String status) {
        return maintainOrderMapper.check(id,status);
    }
}
