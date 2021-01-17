package com.yibo.parking.controller.api;

import com.yibo.parking.entity.car.Lease;
import com.yibo.parking.service.Impl.car.CarServiceImpl;
import com.yibo.parking.service.Impl.car.LeaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/web/V1/Count")
public class CountV1ApiController {

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private LeaseServiceImpl leaseService;

    @ResponseBody
    @RequestMapping(value = "/welcome")
    public String index(){
        //获取车辆租用订单
        List<Lease> leases = leaseService.getOrdersAPI();
        return null;
    }

}
