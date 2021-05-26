package com.yibo.parking.utils;

import com.obs.services.ObsClient;

import java.io.IOException;

public class OBSUtil {

    private String endPoint = "https://obs.cn-north-4.myhuaweicloud.com/";
    private String ak = "BRR9BK8PHJRM0QWR4X7S";
    private String sk = "oHyA7SojBz56IEFe3dVHMZZ16dPezVPyiuaBDM4A";

    //创建OBS实例
    ObsClient obsClient = new ObsClient(ak, sk, endPoint);

    public void init(){

    }

    public void close() throws IOException {
        obsClient.close();
    }
}
