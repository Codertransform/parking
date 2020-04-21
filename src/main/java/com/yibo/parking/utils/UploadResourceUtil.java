package com.yibo.parking.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadResourceUtil implements WebMvcConfigurer {

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    @Value("${file.upload.path.relative}")
    private String fileRelativePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileRelativePath).addResourceLocations("file:/" + filePath);
    }

}
