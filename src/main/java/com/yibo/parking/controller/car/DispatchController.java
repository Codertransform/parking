package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.Dispatch;
import com.yibo.parking.service.Impl.car.DispatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/cars/dispatch")
public class DispatchController {

    @Autowired
    private DispatchServiceImpl dispatchService;

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        model.addAttribute("title","车辆分派");
        return "cars/dispatch/index";
    }

    @RequestMapping(value = "/add")
    public String add(Dispatch dispatch, Model model){
        if (dispatch.getCar() != null && dispatch.getCar().getId() != null){
            model.addAttribute("dispatch", dispatchService.get(dispatch));
        }
        model.addAttribute("title","车辆分派成功");
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
