package com.yibo.parking.utils;

import com.google.gson.Gson;
import com.yibo.parking.entity.util.Json;

public class JsonUtils {

    private static Json json = new Json();

    public static Gson success(Object data){
        json.setCode("0");
        json.setData(data);
        json.setMessage("success");
        Gson gson = new Gson();
        gson.toJson(json);
        return gson;
    }

    public static Gson error(Object data){
        json.setCode("-1");
        json.setData(data);
        json.setMessage("error");
        Gson gson = new Gson();
        gson.toJson(json);
        return gson;
    }
}
