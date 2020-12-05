package com.yibo.parking.controller.work;

import com.yibo.parking.entity.work.Contractor;
import com.yibo.parking.entity.work.ContractorToll;
import com.yibo.parking.entity.work.NormalToll;
import com.yibo.parking.service.Impl.work.ContractorServiceImpl;
import com.yibo.parking.service.Impl.work.NormalTollServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/work/collector/general")
public class NormalTollController {

    @Autowired
    private NormalTollServiceImpl normalTollService;

    @Autowired
    private ContractorServiceImpl contractorService;

    @RequestMapping(value = {"","/"})
    public String index(NormalToll normalToll, Model model){
        List<NormalToll> normalTolls = normalTollService.findList(normalToll);
        model.addAttribute("title","非智能收费员");
        model.addAttribute("normalToll",normalToll);
        model.addAttribute("normalTolls",normalTolls);
        return "work/NormalToll/index";
    }

    @RequestMapping(value = "/add")
    public String add(Model model){
        model.addAttribute("title", "新增收费员");
        return "work/NormalToll/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(NormalToll normalToll, Model model){
        normalToll = normalTollService.get(normalToll);
        model.addAttribute("title", "收费员信息编辑");
        model.addAttribute("normalToll", normalToll);
        return "work/NormalToll/edit";
    }

    @RequestMapping(value = "/bind")
    public String bind(NormalToll normalToll, Model model){
        List<Contractor> contractors = contractorService.findAllList();
        model.addAttribute("contractors", contractors);
        model.addAttribute("normalToll", normalToll);
        model.addAttribute("title", "绑定收费员");
        return "work/NormalToll/bind";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(NormalToll normalToll){
        Map<String,Object> map = normalTollService.save(normalToll);
        int flag = (int) map.get("flag");
        if (flag != 0){
            return JsonUtils.success(normalToll, String.valueOf(map.get("message")));
        }
        return JsonUtils.error(normalToll);
    }

    @ResponseBody
    @RequestMapping(value = "/bindSave")
    public String bindSave(ContractorToll contractorToll){
        Map<String,Object> map = normalTollService.bindSave(contractorToll);
        int flag = (int) map.get("flag");
        if (flag != 0){
            return JsonUtils.success(contractorToll, String.valueOf(map.get("message")));
        }
        return JsonUtils.errorBy(contractorToll, String.valueOf(map.get("message")));
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String delete(NormalToll normalToll){
        Map<String, Object> map = normalTollService.delete(normalToll);
        int flag = (int) map.get("flag");
        if (flag != 0){
            return JsonUtils.success(normalToll, String.valueOf(map.get("message")));
        }
        return JsonUtils.error(normalToll);
    }

    @ResponseBody
    @RequestMapping(value = "/dels")
    public String deletes(@RequestParam("ids[]") List<String> ids){
        Map<String, Object> map = normalTollService.deletes(ids);
        int flag = (int) map.get("flag");
        if (flag == ids.size()){
            return JsonUtils.success(ids, String.valueOf(map.get("message")));
        }else {
            return JsonUtils.error(ids);
        }
    }
}
