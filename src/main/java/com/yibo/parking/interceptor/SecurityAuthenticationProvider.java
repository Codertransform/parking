package com.yibo.parking.interceptor;

import com.yibo.parking.entity.user.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        String userName = authentication.getName();	// 这个获取表单输入中返回的用户名;
        String password = authentication.getCredentials().toString();	// 这个是表单中输入的密码；


        // 这里构建来判断用户是否存在和密码是否正确
        System.out.println("username:"+ userName);
        System.out.println("password:"+ password);


        UserData userData = (UserData) userDetailService.loadUserByUsername(userName);
        if (userData == null)
        {
            throw new BadCredentialsException("用户名不存在");
        }

        System.out.println("userData username:"+ userData.getUsername());
        System.out.println("userData password:"+ userData.getPassword());


        if (!userData.getPassword().equals(password))
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
