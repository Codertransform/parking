package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.TransformData;
import com.yibo.parking.service.Impl.car.TransformDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/cars/position")
public class PositionController {

    @Autowired
    private TransformDataServiceImpl transformDataService;

    @RequestMapping(value = {"","/"})
    public String index(TransformData data, Model model){
        model.addAttribute("title","车辆定位追踪");
        model.addAttribute("data",data);
        return "cars/position/index";
    }

    @ResponseBody
    @RequestMapping(value = "/getCars")
    public List<TransformData> getCars(TransformData data){
        return transformDataService.getLocation(data);
    }
}
