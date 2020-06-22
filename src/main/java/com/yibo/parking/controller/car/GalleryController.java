package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.Gallery;
import com.yibo.parking.service.Impl.car.GalleryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/cars/gallery")
public class GalleryController {

    @Autowired
    private GalleryServiceImpl galleryService;

    @RequestMapping(value = {"","/"})
    public String index(Gallery gallery, Model model){
        List<Gallery> galleries = galleryService.findList(gallery);
        model.addAttribute("galleries",galleries);
        model.addAttribute("title","车辆图库");
        model.addAttribute("count",galleries.size());
        return "cars/gallery/index";
    }

    @ResponseBody
    @RequestMapping(value = "/autoAdd")
    public String autoAdd(){
        galleryService.autoAdd();
        return null;
    }
}
