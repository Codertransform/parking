package com.yibo.parking.utils;

import java.util.UUID;

public class EntityIdGenerate {
    public static String generateId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
