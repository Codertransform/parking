package com.yibo.parking.interceptor;

import com.yibo.parking.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AuthorityInterceptor implements HandlerInterceptor {

    private static final Set<String> NOT_INTERCEPT_URI = new HashSet<>();//不拦截的URI

    private Logger log = LoggerFactory.getLogger(AuthorityInterceptor.class);

    static {
        NOT_INTERCEPT_URI.add("/login");
        NOT_INTERCEPT_URI.add("/ValidateCode");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String uri = request.getRequestURI();
        if (NOT_INTERCEPT_URI.contains(uri)) {
            log.info("不拦截" + uri);
            return true;
        }
        log.info("拦截" + uri);
        HttpSession session = request.getSession();
        User userInfo = (User) session.getAttribute("user");
        if (userInfo == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler
            , ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler
            , Exception ex) throws Exception {

    }
}
