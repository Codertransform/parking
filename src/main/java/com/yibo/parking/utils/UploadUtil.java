package com.yibo.parking.utils;

import com.yibo.parking.interceptor.ApplicationContextUtil;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class UploadUtil {

    private static final Environment environment = ApplicationContextUtil.get(Environment.class);

    public static Map<String,String> upload(MultipartFile picture, String address) {
        Map<String,String> map = new HashMap<>();
        String pictureName = "";
        String fileSavePath = "";
        //获取InputStream
        InputStream in = null;
        //根据文件头获取文件类型
        String type = "";
        try {
            in = picture.getInputStream();
            type = FileType.getFileType(in);
            //获取上传路径
            fileSavePath = environment.getProperty("spring.servlet.multipart.location") + address + "/";
            /**
             * transferTo在开发Web应用程序时比较常见的功能之一，
             * 就是允许用户利用multipart请求将本地文件上传到服务器,
             * Spring通过对ServletAPI的HttpServletRequest接口进行扩展，使其能够很好地处理文件上传
             */
            File file = new File(fileSavePath);
            if (!file.isDirectory()){
                file.mkdirs();
            }
            //设置图片为唯一的id
            pictureName = EntityIdGenerate.generateImgName() + "." + type;
            picture.transferTo(new File(fileSavePath + pictureName));
            map.put("src","/uploadFiles/"+address+"/" + pictureName);
        } catch (IOException e) {
            map.put("errmsg",e.getMessage());
            return map;
        }
        return map;
    }
}
