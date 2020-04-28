package com.yibo.parking.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
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
    public String welcome(){
        return "welcome";
    }

    @RequestMapping(value = "/login")
    public String login( Model model){
        return "login";
    }

    /*@RequestMapping(value = "/loginDo")
    public String toLogin(String username, String password, String validateCode, HttpServletRequest request,
                          HttpSession session, RedirectAttributes redirectAttributes) {
        String loginValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
        if (username == null || username.equals("")){
            redirectAttributes.addFlashAttribute("message", "用户名不能为空！");
            return "redirect:/login";
        }
        if (password == null || password.equals("")){
            redirectAttributes.addFlashAttribute("message", "密码不能为空！");
            return "redirect:/login";
        }
        //验证码不正确
        if(loginValidateCode == null || loginValidateCode.equals("")){
            redirectAttributes.addFlashAttribute("message","验证码过期");//验证码过期
            return "redirect:/login";
        }
        if (!loginValidateCode.equals(validateCode)){
            redirectAttributes.addFlashAttribute("message", "验证码不正确");//验证码正确
            return "redirect:/login";
        }
        User user = userService.get(username,password);
        if (user != null && user.getId() != null){
            session.setAttribute(user.getId(),user);
            redirectAttributes.addFlashAttribute("message","恭喜登陆成功，欢迎回来！");
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("message","用户名密码错误");
        return "redirect:/login";
    }*/

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
