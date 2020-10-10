package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.TrackFo;
import com.yibo.parking.service.Impl.car.CarServiceImpl;
import com.yibo.parking.service.Impl.car.TrackFoServiceImpl;
import com.yibo.parking.service.Impl.car.TrackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/cars/track/follow")
public class TrackFoController {

    @Autowired
    private TrackFoServiceImpl trackFoService;

    @Autowired
    private TrackServiceImpl trackService;

    @Autowired
    private CarServiceImpl carService;

    @RequestMapping(value = {"","/"})
    public String follow(TrackFo trackFo, Model model){
        List<TrackFo> trackFos = trackFoService.findList(trackFo);
        model.addAttribute("title", "轨迹跟随");
        model.addAttribute("trackFos", trackFos);
        model.addAttribute("trackFo", trackFo);
        return "/cars/track/follow/index";
    }

    @RequestMapping(value = "/add")
    public String add(Model model){
        model.addAttribute("cars", carService.getAllCars());
        return "/cars/track/follow/add";
    }
}
