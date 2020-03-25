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
        List<Unit> units = unitService.getUnits();
        model.addAttribute("units",units);
        return "unit/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Unit unit, Model model){
        List<Unit> units = unitService.getUnitsBy(unit);
        model.addAttribute("units",units);
        return "unit/add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String toAdd(Unit unit){
        int i = unitService.save(unit);
        if (i != 0)
            return JsonUtils.success(unit,"添加申请单位成功");
        else
            return JsonUtils.error(unit);
    }
}
