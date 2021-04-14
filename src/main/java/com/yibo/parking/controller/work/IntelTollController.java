package com.yibo.parking.controller.work;

import com.yibo.parking.entity.work.IntelToll;
import com.yibo.parking.service.Impl.work.IntelTollServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/work/collector/intelligent")
public class IntelTollController {

    @Autowired
    private IntelTollServiceImpl intelTollService;

    @RequestMapping(value = {"","/"})
    public String index(IntelToll intelToll, Model model){
        List<IntelToll> intelTolls = intelTollService.findList(intelToll);
        model.addAttribute("title","智能收费员");
        model.addAttribute("intelToll",intelToll);
        model.addAttribute("intelTolls",intelTolls);
        return "work/intelToll/index";
    }

    @RequestMapping(value = "/add")
    public String add(){
        return "work/intelToll/add";
    }
}
