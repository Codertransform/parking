package com.yibo.parking.controller;

import com.yibo.parking.entity.user.User;
import com.yibo.parking.service.Impl.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping({"","/"})
    public String index(){
        return "index-2";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("user","user");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String toLogin(String username, String password, HttpSession session, Model model){
        User user = userService.get(username,password);
        if (user != null && user.getId() != null){
            session.setAttribute("user",user);
            return "redirect:/";
        }
        model.addAttribute("message","用户名密码错误");
        return "redirect:/login";
    }
}
