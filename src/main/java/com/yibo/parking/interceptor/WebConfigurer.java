package com.yibo.parking.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludePath = new String[]{"/static/**", "/lib/**"};
        registry.addInterceptor(new AuthorityInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(excludePath);
    }
}
