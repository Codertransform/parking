package com.yibo.parking.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {

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
        String password = passwordEncoder().encode(authentication.getCredentials().toString());	// 这个是表单中输入的密码；

        System.out.println(password);

        // 这里构建来判断用户是否存在和密码是否正确
        UserDetails userData = userDetailService.loadUserByUsername(userName);
        System.out.println(userData.getPassword());
        if (userData == null)
        {
            throw new BadCredentialsException("用户名不存在");
        }

        if (!passwordEncoder().matches(userData.getPassword(),password))
        {
            System.out.println("密码不正确");
            throw new BadCredentialsException("密码不正确");
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
