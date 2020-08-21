package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.Car;
import com.yibo.parking.entity.car.Dispatch;
import com.yibo.parking.entity.unit.Unit;
import com.yibo.parking.service.Impl.car.DispatchServiceImpl;
import com.yibo.parking.service.Impl.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/cars/dispatch")
public class DispatchController {

    @Autowired
    private DispatchServiceImpl dispatchService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = {"","/"})
    public String index(Dispatch dispatch, Model model){
        List<Unit> units = dispatchService.findUnitList();
        List<Dispatch> dispatches = dispatchService.findList(dispatch);
        model.addAttribute("title","车辆分派");
        model.addAttribute("units",units);
        model.addAttribute("dispatches",dispatches);
        model.addAttribute("count",dispatches.size());
        model.addAttribute("dispatch",dispatchService.get(dispatch));
        return "cars/dispatch/index";
    }

    @RequestMapping(value = "/add")
    public String add(Model model){
        List<Unit> units = dispatchService.findUnitList();
        List<Car> cars = dispatchService.findCars();
        model.addAttribute("title","车辆分派成功");
        model.addAttribute("user",userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("units",units);
        model.addAttribute("cars",cars);
        return "cars/dispatch/add";
    }

    @ResponseBody
    @RequestMapping(value = "/adds")
    public String adds(String[] ids){
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(Dispatch dispatch){
        return "";
    }
}
