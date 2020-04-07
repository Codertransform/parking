package com.yibo.parking.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class EntityIdGenerate {

    public static String generateId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String generateImgName(){
        Random random = new Random();
        // 随机数的量 自由定制，这是5位随机数
        int r = random.nextInt(90000) + 10000;

        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String timeStr = sdf.format(new Date());
        return "IMG" + timeStr + r;
    }
}
