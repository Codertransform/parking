package com.yibo.parking.controller.complaint;

import com.google.gson.Gson;
import com.yibo.parking.entity.Complaint;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/complaint")
public class compController {

    @RequestMapping({"","/"})
    public String Index(Model model){
        model.addAttribute("title","投诉信息");
        return "complaint/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(Model model){
        return "complaint/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Gson Add(Complaint complaint, Model model){
        return JsonUtils.error(complaint);
    }

    public String edit(){
        return null;
    }

    public String toEdit(){
        return "";
    }
}
