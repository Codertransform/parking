package com.yibo.parking.service.Impl.system;

import com.alibaba.fastjson.JSONObject;
import com.yibo.parking.dao.system.SystemDataMapper;
import com.yibo.parking.dao.system.SystemServerMapper;
import com.yibo.parking.entity.system.SystemData;
import com.yibo.parking.entity.system.SystemServer;
import com.yibo.parking.service.SystemServerService;
import com.yibo.parking.utils.EntityIdGenerate;
import com.yibo.parking.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemServerServiceImpl implements SystemServerService {

    @Autowired
    private SystemServerMapper serverMapper;

    @Autowired
    private SystemDataMapper dataMapper;

    @Override
    public List<SystemServer> findList(SystemServer systemServer) {
        return serverMapper.findList(systemServer);
    }

    @Override
    public int insert(SystemServer systemServer) throws Exception {
        SystemData data = dataMapper.getByKey("高德","key");
        if (data == null){
            throw new Exception("请先添加高德密钥");
        }
        String url = "https://tsapi.amap.com/v1/track/service/add";
        Map<String,String> map = new HashMap<>();
        map.put("key",data.getValue());
        map.put("name",systemServer.getName());
        String result = HttpClientUtil.doPost(url,map);
        JSONObject object = JSONObject.parseObject(result);
        systemServer.setId(EntityIdGenerate.generateId());
        if (object.get("errcode").equals("10000")){
            JSONObject json = object.getJSONObject("data");
            systemServer.setsId(json.get("sid").toString());
        }
        return serverMapper.insert(systemServer);
    }

    @Override
    public int update(SystemServer systemServer) {
        return 0;
    }

    @Override
    public int delete(SystemServer systemServer) {
        return 0;
    }
}
