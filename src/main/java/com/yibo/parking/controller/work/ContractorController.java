package com.yibo.parking.controller.work;

import com.yibo.parking.entity.system.SystemData;
import com.yibo.parking.entity.work.Contractor;
import com.yibo.parking.service.Impl.system.SystemDataServiceImpl;
import com.yibo.parking.service.Impl.work.ContractorServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/work/contractor")
public class ContractorController {

    @Autowired
    private ContractorServiceImpl contractorService;

    @Autowired
    private SystemDataServiceImpl dataService;

    @RequestMapping(value = {"","/"})
    public String index(Contractor contractor, Model model){
        List<Contractor> contractors = contractorService.findList(contractor);
        model.addAttribute("title", "承包人管理");
        model.addAttribute("contractor", contractor);
        model.addAttribute("contractors", contractors);
        return "work/contractor/index";
    }

    @RequestMapping(value = "/add")
    public String add(Model model){
        List<SystemData> dataList = dataService.findByAd();
        model.addAttribute("title", "添加承包人");
        model.addAttribute("dataList", dataList);
        return "work/contractor/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Contractor contractor, Model model){
        model.addAttribute("contractor", contractor);
        return "work/contractor/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(Contractor contractor){
        Map<String, Object> map = contractorService.save(contractor);
        int flag = (int) map.get("flag");
        if (flag != 0){
            return JsonUtils.success(contractor, String.valueOf(map.get("message")));
        }
        return JsonUtils.error(contractor);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Contractor contractor){
        int d = contractorService.delete(contractor);
        if (d != 0) {
            return JsonUtils.success(contractor, "承包人信息删除成功！");
        }
        return JsonUtils.error(contractor);
    }
}
