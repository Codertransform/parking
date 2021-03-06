package com.yibo.parking.controller.complaint;

import com.google.gson.Gson;
import com.yibo.parking.entity.complaint.Complaint;
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
    public Object Add(Complaint complaint){
        return JsonUtils.error(complaint);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model){
        model.addAttribute("title","123");
        return "";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String toEdit(){
        return "";
    }
}
