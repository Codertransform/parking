package com.yibo.parking.service.Impl.car;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yibo.parking.dao.device.DeviceMapper;
import com.yibo.parking.dao.car.TrackMapper;
import com.yibo.parking.entity.device.Device;
import com.yibo.parking.entity.car.Track;
import com.yibo.parking.service.TrackService;
import com.yibo.parking.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackMapper trackMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<Track> findList(Track track) {
        return trackMapper.findList(track);
    }

    @Override
    public JSONArray get(Track track) {
        Device device = new Device();
        device.setCarId(track.getCarId());
        Device dev = deviceMapper.get(device);
        String url = "https://tsapi.amap.com/v1/track/terminal/trsearch";
        Map<String,String> map = new HashMap<>();
        map.put("key","1c92d37732848ca864c4daac21454294");
        map.put("sid",dev.getsId());
        map.put("tid",dev.gettId());
        map.put("trid",track.getTrackId());
        map.put("pagesize","999");
        map.put("correction", "denoise=1,mapmatch=1");
        String result = HttpClientUtil.doGet(url,map);
        JSONObject object = JSONObject.parseObject(result);
        System.out.println(object);
        JSONArray array = new JSONArray();
        if (object.get("errcode").toString().equals("10000")) {
            JSONObject jsonObject = object.getJSONObject("data");
            array = jsonObject.getJSONArray("tracks");
            JSONObject json = array.getJSONObject(0);
            int count = Integer.parseInt(json.get("counts").toString());
            if (count > 1000){
                Map<String,String> m = new HashMap<>();
                map.put("key","1c92d37732848ca864c4daac21454294");
                map.put("sid",dev.getsId());
                map.put("tid",dev.gettId());
                map.put("trid",track.getTrackId());
                map.put("page","2");
                map.put("correction", "denoise=1,mapmatch=1");
                String res = HttpClientUtil.doGet(url,m);
                JSONObject o = JSONObject.parseObject(res);
                System.out.println(o);
            }
        }
        return array;
    }

    @Override
    public int insert(Track track) {
        return 0;
    }

    @Override
    public int update(Track track) {
        return 0;
    }

    @Override
    public int delete(Track track) {
        return 0;
    }
}
