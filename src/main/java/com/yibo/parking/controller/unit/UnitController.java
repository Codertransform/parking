package com.yibo.parking.controller.unit;

import com.yibo.parking.entity.unit.Unit;
import com.yibo.parking.service.Impl.unit.UnitServiceIpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/unit")
public class UnitController {

    @Autowired
    private UnitServiceIpml unitService;

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        List<Unit> unitsList = unitService.getUnits();
        String units = unitService.List2Josn(unitsList);
        System.out.println(units);
        model.addAttribute("units", units);
        return "unit/index";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpSession session, Model model){
        List<Unit> units = unitService.getUnits();
        model.addAttribute("units",units);
        if (session.getAttribute("message") != null)
        model.addAttribute("message",session.getAttribute("message"));
        return "unit/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String toAdd(HttpServletRequest request, Unit unit, HttpSession session){
        int i = unitService.save(unit);
        if (i != 0){
            session.setAttribute("message","添加成功！");
        }else{
            session.setAttribute("message","添加失败，请联系管理员");
        }
        return "redirect:" + request.getHeader("Origin") + "/unit/add";
    }
}
