package com.yibo.parking.controller.system;

import com.yibo.parking.entity.system.RentalStrategy;
import com.yibo.parking.service.Impl.system.RentalStrategyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/system/rental/strategy")
public class RentalStrategyController {

    @Autowired
    private RentalStrategyServiceImpl rentalStrategyService;

    @RequestMapping(value = {"","/"})
    public String index(RentalStrategy rentalStrategy, Model model){
        List<RentalStrategy> list = rentalStrategyService.findList(rentalStrategy);
        model.addAttribute("title", "租车策略");
        model.addAttribute("list", list);
        return "system/rental/strategy/index";
    }
}
