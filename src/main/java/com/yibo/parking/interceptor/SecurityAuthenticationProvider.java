package com.yibo.parking.interceptor;

import com.yibo.parking.entity.user.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {

    Logger logger = LoggerFactory.getLogger(SecurityAuthenticationProvider.class);

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    HttpServletRequest request;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        String userName = authentication.getName();	// 这个获取表单输入中返回的用户名;
        String password = authentication.getCredentials().toString();	// 这个是表单中输入的密码；


        // 这里构建来判断用户是否存在和密码是否正确
        UserData userData = (UserData) userDetailService.loadUserByUsername(userName);
        if (userData == null)
        {
            throw new BadCredentialsException("用户不存在");
        }

        if (!passwordEncoder().matches(password,userData.getPassword()))
        {
            System.out.println("密码不正确");
            throw new BadCredentialsException("密码不正确");
        }
        String requestCode = request.getParameter("validateCode");
        HttpSession session = request.getSession();
        String saveCode = (String) session.getAttribute("login_validate_code");//captcha
        //获取到session验证码后随时清除
        if(!StringUtils.isEmpty(saveCode)) {
            session.removeAttribute("RANDOMVALIDATECODEKEY");//captcha
        }
        logger.info("requestCode:"+requestCode+",saveCode:"+saveCode);
        if(StringUtils.isEmpty(saveCode) || StringUtils.isEmpty(requestCode) || !requestCode.equals(saveCode)) {
            logger.info("图片验证码错误！");
            throw new DisabledException("图形验证码错误！");
        }
        Collection<? extends GrantedAuthority> authorities = userData.getAuthorities();
        // 构建返回的用户登录成功的token
        System.out.println("登陆成功");
        return new UsernamePasswordAuthenticationToken(userData, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
