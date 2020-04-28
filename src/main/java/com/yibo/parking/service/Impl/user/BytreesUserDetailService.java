package com.yibo.parking.service.Impl.user;

import com.yibo.parking.entity.user.User;
import com.yibo.parking.entity.user.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class BytreesUserDetailService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = null;

        // TODO 根据用户名，查找到对应的密码，与权限
        System.out.println("get name:" + username);

        User user=userService.findByName(username);

        if(user != null)
        {
            System.out.println("找到用户名和密码:"+ user.toString());
            //返回的用户信息如下，参数分别是：用户名，密码，用户权限
            if(user.getUsername().equals("admin"))
                userData=new UserData(user.getUsername(), user.getPassword(), "ROLE_ADMIN", true,true,true,true);//ROLE_ADMIN
            else
                userData=new UserData(user.getUsername(), user.getPassword(), "ROLE_USER", true,true,true,true); //ROLE_USER
        }
        else
        {
            System.out.println("没有找到用户名和密码");
        }

        return userData;
    }
}
