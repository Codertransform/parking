package com.yibo.parking.utils;

import com.google.gson.Gson;
import com.yibo.parking.entity.util.Json;

public class JsonUtils {

    private static Json json = new Json();

    public static String success(Object data){
        json.setCode("0");
        json.setData(data);
        json.setMessage("操作成功");
        Gson gson = new Gson();
        return gson.toJson(json);
    }

    public static String error(Object data){
        json.setCode("-1");
        json.setData(data);
        json.setMessage("操作失败,请联系管理员");
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
