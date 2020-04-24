package com.yibo.parking.utils;

import javax.servlet.http.HttpServletRequest;

public class RedirectURLUtils {

    public static String getHost(HttpServletRequest request){
        System.out.println(request.getScheme());
        System.out.println(request.getServerName());
        String url = request.getScheme() + "://" + request.getServerName();
        System.out.println(url);
        return url;
    }
}
