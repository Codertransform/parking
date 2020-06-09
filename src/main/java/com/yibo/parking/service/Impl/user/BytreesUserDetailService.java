package com.yibo.parking.service.Impl.user;

import com.yibo.parking.entity.user.User;
import com.yibo.parking.entity.user.UserData;
import com.yibo.parking.interceptor.SecurityAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class BytreesUserDetailService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(SecurityAuthenticationProvider.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private HttpServletRequest request;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = null;

        // TODO 根据用户名，查找到对应的密码，与权限
        if(StringUtils.isEmpty(username)) {
            logger.info("用户名不能为空！");
            throw new UsernameNotFoundException("用户名不能为空！");
        }

        User user=userService.findByName(username);

        if(user != null){
            System.out.println("找到用户名和密码:"+ user.toString());
            //返回的用户信息如下，参数分别是：用户名，密码，用户权限
            if(user.getUsername().equals("admin"))
                userData=new UserData(user.getUsername(), user.getPassword(), "ROLE_ADMIN", true,true,true,true);//ROLE_ADMIN
            else
                userData=new UserData(user.getUsername(), user.getPassword(), "ROLE_USER", true,true,true,true); //ROLE_USER
        }else{
            System.out.println("没有找到用户名和密码");
        }

        String validateCode = request.getParameter("validateCode");

        if(StringUtils.isEmpty(validateCode) || validateCode.equals("验证码:")) {
            logger.info("验证码不能为空！");
            throw new UsernameNotFoundException("验证码不能为空！");
        }

        return userData;
    }
}
