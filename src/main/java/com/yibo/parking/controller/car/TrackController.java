package com.yibo.parking.controller.car;

import com.alibaba.fastjson.JSONArray;
import com.yibo.parking.entity.car.Track;
import com.yibo.parking.service.Impl.car.TrackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/cars/track")
public class TrackController {

    @Autowired
    private TrackServiceImpl trackService;

    @RequestMapping(value = {"","/"})
    public String index(Track track, Model model){
        List<Track> tracks = trackService.findList(track);
        model.addAttribute("title", "车辆轨迹管理");
        model.addAttribute("tracks", tracks);
        model.addAttribute("track", track);
        if (tracks != null) {
            model.addAttribute("count", tracks.size());
        }
        return "cars/track/index";
    }

    @RequestMapping(value = "/replay")
    public String replay(String trackId, String carId, Model model){
        model.addAttribute("trackId", trackId);
        model.addAttribute("carId", carId);
        return "cars/track/replay";
    }


    @ResponseBody
    @RequestMapping(value = "/getData")
    public JSONArray getData(Track track){
        return trackService.get(track);
    }
}
