package com.yibo.parking.controller.unit;

import com.yibo.parking.entity.unit.Unit;
import com.yibo.parking.service.Impl.unit.UnitServiceIpml;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/unit")
public class UnitController {

    @Autowired
    private UnitServiceIpml unitService;

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        model.addAttribute("title","单位管理");
        return "unit/index";
    }

    @ResponseBody
    @RequestMapping(value = "/getTree")
    public String getTree(){
        List<Unit> unitsList = unitService.getUnits();
        return unitService.List2Josn(unitsList);
    }

    @RequestMapping(value = "/list")
    public String list(String id, Model model){
        model.addAttribute("title","查看详情");
        model.addAttribute("unit",unitService.get(id));
        return "unit/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Unit unit, Model model){
        if (unit.getParentId() != null){
            unitService.clearUnits();
            List<Unit> units = unitService.getSonUnits(unit.getParentId());
            model.addAttribute("units",units);
        }else {
            List<Unit> units = unitService.getUnitsBy(unit);
            model.addAttribute("units",units);
        }
        unit.setId(unit.getParentId());
        model.addAttribute("unit",unitService.get(unit.getId()));
        return "unit/add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String toAdd(Unit unit){
        int i = unitService.save(unit);
        if (i != 0){
            return JsonUtils.success(unit,"添加成功！");
        }else{
            return JsonUtils.error(unit);
        }
    }
}
