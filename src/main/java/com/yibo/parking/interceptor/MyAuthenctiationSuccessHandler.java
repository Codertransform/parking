package com.yibo.parking.interceptor;

import com.yibo.parking.entity.unit.Unit;
import com.yibo.parking.entity.user.User;
import com.yibo.parking.service.Impl.unit.UnitServiceIpml;
import com.yibo.parking.service.Impl.user.UserServiceImpl;
import com.yibo.parking.utils.IPUtil;
import com.yibo.parking.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenctiationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UnitServiceIpml unitServiceIpml;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("security登陆成功拦截！");
        RequestCache requestCache = new HttpSessionRequestCache();

        User user = userService.findByName(authentication.getName());
        user.setLogin_ip(IPUtil.getIpAddr(request));
        user.setLogin_time(TimeUtil.getTime());
        userService.loginUpdate(user);

        Unit unit = unitServiceIpml.findByUser(user);
        if (unit != null){
            user.setUnit(unit);
        }
        redisTemplate.opsForValue().set(user.getId(),user);

        String url = null;
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            url = savedRequest.getRedirectUrl();
        }
        if(url == null){
            getRedirectStrategy().sendRedirect(request,response,"/");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
