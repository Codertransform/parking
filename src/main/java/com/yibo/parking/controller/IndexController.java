package com.yibo.parking.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.yibo.parking.entity.user.User;
import com.yibo.parking.service.Impl.user.UserServiceImpl;
import com.yibo.parking.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @Resource
    private DefaultKaptcha captchaProducer;

    @Autowired
    private UserServiceImpl userService;

    /**
     * 登录验证码SessionKey
     */
    public static final String LOGIN_VALIDATE_CODE = "login_validate_code";

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",auth.getName());
        return "index-2";
    }

    @RequestMapping(value = "/welcome")
    public String welcome(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByName(auth.getName());
        model.addAttribute("user",user);
        return "welcome";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    /**
     * 登录验证码图片
     */
    @RequestMapping(value = "/ValidateCode")
    public void loginValidateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        CommonUtils.validateCode(request,response,captchaProducer,LOGIN_VALIDATE_CODE);
    }

    @RequestMapping(value = "/logout")
    public String logOut(){
        return "logout";
    }
}
