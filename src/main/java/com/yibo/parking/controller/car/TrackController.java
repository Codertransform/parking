package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.Track;
import com.yibo.parking.service.Impl.car.TrackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "/cars/track/index";
    }

    @RequestMapping(value = "/replay")
    public String replay(String id){
        return "/cars/track/replay";
    }

}
