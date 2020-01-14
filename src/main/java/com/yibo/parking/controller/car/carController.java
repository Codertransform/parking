package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.Car;
import com.yibo.parking.service.Impl.car.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/cars")
public class carController {

    @Autowired
    private CarServiceImpl carService;

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        List<Car> list = carService.getCars();
        model.addAttribute("title","车辆管理");
        model.addAttribute("list",list);
        model.addAttribute("count", list.size());
        return "cars/index";
    }
}
