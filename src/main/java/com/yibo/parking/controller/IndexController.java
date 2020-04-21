package com.yibo.parking.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.yibo.parking.entity.user.User;
import com.yibo.parking.service.Impl.user.UserServiceImpl;
import com.yibo.parking.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
    public String index(){
        return "index-2";
    }

    @RequestMapping(value = "/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("user","user");
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String toLogin(String username, String password, String validateCode, HttpServletRequest request, HttpSession session, Model model){
        String loginValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
        Map<String,Object> map = new HashMap<String,Object>();
        if(loginValidateCode == null){
            map.put("status",null);//验证码过期
        }else if(loginValidateCode.equals(validateCode)){
            map.put("status",true);//验证码正确
        }else {
            map.put("status",false);//验证码不正确
        }
        map.put("code",200);
        User user = userService.get(username,password);
        if (user != null && user.getId() != null){
            session.setAttribute("user",user);
            return "redirect:/";
        }
        model.addAttribute("message","用户名密码错误");
        return "redirect:/login";
    }


    /**
     * 登录验证码图片
     */
    @RequestMapping(value = {"/ValidateCode"})
    public void loginValidateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        CommonUtils.validateCode(request,response,captchaProducer,LOGIN_VALIDATE_CODE);
    }

    /**
     * 检查验证码是否正确
     */
    @RequestMapping("/checkLoginValidateCode")
    @ResponseBody
    public Map<String,Object> checkLoginValidateCode(HttpServletRequest request, @RequestParam("validateCode")String validateCode) {
        String loginValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
        Map<String,Object> map = new HashMap<String,Object>();
        if(loginValidateCode == null){
            map.put("status",null);//验证码过期
        }else if(loginValidateCode.equals(validateCode)){
            map.put("status",true);//验证码正确
        }else {
            map.put("status",false);//验证码不正确
        }
        map.put("code",200);
        return map;
    }
}
