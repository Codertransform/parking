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
    public String list(Unit unit, Model model){
        unitService.clearUnits();
        List<Unit> units = unitService.getUnitsByU(unit);
        model.addAttribute("title","组织列表");
        model.addAttribute("units",units);
        model.addAttribute("count",units.size());
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

    @RequestMapping(value = "/edit")
    public String edit(Unit unit, Model model){
        model.addAttribute("title","组织信息修改");
        model.addAttribute("unit",unitService.get(unit.getId()));
        model.addAttribute("units",unitService.getUnits());
        return "unit/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String toEdit(Unit unit){
        int e = unitService.save(unit);
        return e != 0 ? JsonUtils.success(unit,"组织信息修改成功") : JsonUtils.error(unit);
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(Unit unit){
        int d = unitService.delete(unit.getId());
        return d != 0 ? JsonUtils.success(unit,"组织信息删除成功") : JsonUtils.error(unit);
    }
}
