package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.Car;
import com.yibo.parking.entity.car.Dispatch;
import com.yibo.parking.entity.unit.Unit;
import com.yibo.parking.service.Impl.car.DispatchServiceImpl;
import com.yibo.parking.service.Impl.user.UserServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
        if (dispatch.getUnit() == null){
            Unit unit = new Unit();
            unit.setId("");
            dispatch.setUnit(unit);
        }
        if (dispatch.getCar() == null) {
            Car car = new Car();
            car.setId("");
            dispatch.setCar(car);
        }
        model.addAttribute("dispatch",dispatch);
        return "cars/dispatch/index";
    }

    @RequestMapping(value = "/add")
    public String add(Dispatch dispatch, Model model){
        List<Unit> units = dispatchService.findUnitList();
        List<Car> cars = dispatchService.findCars();
        model.addAttribute("title","车辆分派");
        model.addAttribute("user",userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("units",units);
        model.addAttribute("cars",cars);
        if (dispatch.getCar() != null) {
            model.addAttribute("car",dispatchService.getCar(dispatch.getCar()));
        }
        return "cars/dispatch/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Dispatch dispatch, Model model){
        List<Unit> units = dispatchService.findUnitList();
        List<Car> cars = dispatchService.findCars();
        model.addAttribute("title", "分派信息变更");
        model.addAttribute("dispatch", dispatchService.get(dispatch));
        model.addAttribute("units",units);
        model.addAttribute("cars",cars);
        return "cars/dispatch/edit";
    }

    @RequestMapping(value = "/adds")
    public String adds(@RequestParam("ids[]") String[] ids, Model model){
        List<Unit> units = dispatchService.findUnitList();
        model.addAttribute("user",userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("units",units);
        model.addAttribute("ids",ids);
        return "cars/dispatch/adds";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(Dispatch dispatch){
        Map<String, Object> map = dispatchService.save(dispatch);
        int flag = (int) map.get("flag");
        if (flag != 0) {
            return JsonUtils.success(dispatch, String.valueOf(map.get("message")));
        }
        return JsonUtils.error(dispatch);
    }

    @ResponseBody
    @RequestMapping(value = "/ids")
    public String ids(@RequestParam("ids[]") String[] ids){
        dispatchService.saveRedis(ids);
        return JsonUtils.success(ids,"加入缓存成功");
    }

    @ResponseBody
    @RequestMapping(value = "/saves")
    public String saves(Dispatch dispatch){
        return null;
    }
}
