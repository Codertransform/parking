package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.MaintainOrderMapper;
import com.yibo.parking.entity.car.MaintainOrder;
import com.yibo.parking.service.MaintainOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintainOrderServiceImpl implements MaintainOrderService {

    @Autowired
    private MaintainOrderMapper maintainOrderMapper;

    @Override
    public List<MaintainOrder> getOrders() {
        return maintainOrderMapper.getOrders();
    }

    public int insert(MaintainOrder order) {
        order.setStatus("-1");
        return maintainOrderMapper.insert(order);
    }
}
