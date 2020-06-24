package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.Gallery;
import com.yibo.parking.entity.car.GalleryInfo;
import com.yibo.parking.service.Impl.car.GalleryServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/autoCreate")
    public String autoCreate(){
        Map<String,Object> map = galleryService.autoCreate();
        boolean flag = (boolean) map.get("flag");
        if (flag){
            return JsonUtils.orderApiSuccess(map.get("code").toString(),map.get("data"),map.get("message").toString());
        }
        return JsonUtils.orderApiError(map.get("code").toString(), map.get("message").toString());
    }

    @RequestMapping(value = "/edit")
    public String edit(Gallery gallery, Model model){
        gallery = galleryService.get(gallery);
        model.addAttribute("gallery",gallery);
        return "cars/gallery/add";
    }

    @ResponseBody
    @RequestMapping(value = "/thumb/upload",method = RequestMethod.POST)
    public String upload(@RequestPart("file") MultipartFile picture) {
        Map<String,String> path = galleryService.upload(picture);
        if (path.containsKey("src")){
            System.out.println(path.get("src"));
            return JsonUtils.success(path,"上传成功");
        }else {
            return JsonUtils.error(path);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(Gallery gallery){
        int i =galleryService.update(gallery);
        if (i != 0) {
            return JsonUtils.success(gallery,"相册修改成功");
        }
        return JsonUtils.error(gallery);
    }

    @RequestMapping(value = "/show")
    public String show(GalleryInfo info, Model model) throws Exception {
        String name = galleryService.getName(info);
        List<GalleryInfo> infos = galleryService.findInfos(info);
        model.addAttribute("name",name);
        model.addAttribute("infos",infos);
        return "cars/gallery/show";
    }
}
