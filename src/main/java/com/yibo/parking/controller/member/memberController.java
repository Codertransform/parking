package com.yibo.parking.controller.member;

import com.yibo.parking.entity.member.Member;
import com.yibo.parking.service.Impl.member.MemberServiceImpl;
import com.yibo.parking.service.Impl.unit.UnitServiceIpml;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/member")
public class memberController {

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private UnitServiceIpml unitService;

    @RequestMapping(value = {"","/"})
    public String index(Member member, Model model){
        List<Member> members = memberService.getMembers(member);
        model.addAttribute("member",member);
        model.addAttribute("members",members);
        model.addAttribute("count",members.size());
        model.addAttribute("title","人员列表");
        return "member/index";
    }

    @RequestMapping(value = "/add")
    public String add(Model model){
        model.addAttribute("units",unitService.getUnits());
        return "member/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Member member, Model model){
        model.addAttribute("member",memberService.get(member));
        model.addAttribute("units",unitService.getUnits());
        return "member/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(Member member){
        Map<String,Object> map = memberService.save(member);
        int s = (int) map.get("code");
        if (s == 0) {
            return JsonUtils.success(member, String.valueOf(map.get("message")));
        }else {
            return JsonUtils.error(member);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(Member member){
        Map<String,Object> map = memberService.delete(member);
        int d = (int) map.get("code");
        if (d == 0) {
            return JsonUtils.success(member, String.valueOf(map.get("message")));
        }
        return JsonUtils.error(member);
    }

    @ResponseBody
    @RequestMapping(value = "/status")
    public String status(Member member){
        Map<String,Object> map = memberService.status(member);
        int s = (int) map.get("code");
        if (s != 0){
            return JsonUtils.success(member, String.valueOf(map.get("message")));
        }
        return JsonUtils.error(member);
    }
}
