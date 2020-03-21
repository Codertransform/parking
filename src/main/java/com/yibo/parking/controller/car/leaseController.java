package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.Lease;
import com.yibo.parking.service.Impl.car.LeaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/lease")
public class leaseController {

    @Autowired
    private LeaseServiceImpl leaseService;

    @RequestMapping(value = {"","/"})
    public String index(Lease lease,Model model){
        List<Lease> leases = leaseService.getLeases(lease);
        int count = leases.size();
        model.addAttribute("leases",leases);
        model.addAttribute("count",count);
        model.addAttribute("title","车辆租用");
        return "hire/index";
    }
}
