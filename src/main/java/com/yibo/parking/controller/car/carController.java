package com.yibo.parking.controller.car;

import com.google.gson.Gson;
import com.yibo.parking.entity.car.Car;
import com.yibo.parking.service.Impl.car.CarServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        return "cars/add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String toAdd(Car car){
        int i = carService.save(car);
        if (i == 1){
            return JsonUtils.success(car);
        }else {
            return JsonUtils.error(car);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(String id){
        int d = carService.del(id);
        if (d == 1){
            return JsonUtils.success(id);
        }else {
            return JsonUtils.error(id);
        }
    }
}
