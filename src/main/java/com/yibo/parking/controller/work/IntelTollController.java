package com.yibo.parking.controller.work;

import com.yibo.parking.entity.work.IntelToll;
import com.yibo.parking.service.Impl.system.SystemDataServiceImpl;
import com.yibo.parking.service.Impl.work.IntelTollServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/work/collector/intelligent")
public class IntelTollController {

    @Autowired
    private IntelTollServiceImpl intelTollService;

    @Autowired
    private SystemDataServiceImpl systemDataService;

    @RequestMapping(value = {"","/"})
    public String index(IntelToll intelToll, Model model){
        List<IntelToll> intelTolls = intelTollService.findList(intelToll);
        model.addAttribute("title","智能收费员");
        model.addAttribute("intelToll",intelToll);
        model.addAttribute("intelTolls",intelTolls);
        return "work/intelToll/index";
    }

    @RequestMapping(value = "/add")
    public String add(Model model){
        model.addAttribute("supplies",intelTollService.getDatas());
        return "work/intelToll/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(IntelToll intelToll, Model model){
        model.addAttribute("intelToll", intelTollService.get(intelToll));
        model.addAttribute("supplies",intelTollService.getDatas());
        return "work/intelToll/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(IntelToll intelToll){
        System.out.println(intelToll.getSupplies());
        Map<String, Object> map = intelTollService.save(intelToll);
        int flag = (int) map.get("flag");
        if (flag != 0) {
            return JsonUtils.success(intelToll, String.valueOf(map.get("message")));
        }else {
            return JsonUtils.errorBy(intelToll, String.valueOf(map.get("message")));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String delete(IntelToll intelToll){
        Map<String, Object> map = intelTollService.delete(intelToll);
        int flag = (int) map.get("flag");
        if (flag != 0){
            return JsonUtils.success(intelToll, String.valueOf(map.get("message")));
        }else {
            return JsonUtils.errorBy(intelToll, String.valueOf(map.get("message")));
        }
    }
}
