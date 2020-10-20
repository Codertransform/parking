package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.TrackFo;
import com.yibo.parking.service.Impl.car.CarServiceImpl;
import com.yibo.parking.service.Impl.car.TrackFoServiceImpl;
import com.yibo.parking.service.Impl.car.TrackServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
    public String index(TrackFo trackFo, Model model){
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

    @RequestMapping(value = "/edit")
    public String edit(TrackFo trackFo, Model model){
        trackFo = trackFoService.get(trackFo);
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("trackFo", trackFo);
        return "/cars/track/follow/update";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(TrackFo trackFo){
        Map<String, Object> trackInfo = trackFoService.save(trackFo);
//        int flag = (int) trackfo.get("flag");
        return JsonUtils.success(trackFo, String.valueOf(trackInfo.get("message")));
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String delete(TrackFo trackFo){
        if (trackFoService.delete(trackFo) != 0){
            return JsonUtils.success(trackFo,"删除成功！");
        }
        return JsonUtils.error(trackFo);
    }
}
