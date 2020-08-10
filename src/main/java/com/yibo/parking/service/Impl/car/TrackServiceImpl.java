package com.yibo.parking.service.Impl.car;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yibo.parking.dao.car.DeviceMapper;
import com.yibo.parking.dao.car.TrackMapper;
import com.yibo.parking.entity.car.Device;
import com.yibo.parking.entity.car.Track;
import com.yibo.parking.service.TrackService;
import com.yibo.parking.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Track> list = new ArrayList<>();
        List<Track> tracks = trackMapper.findList(track);
        for (Track t : tracks) {
            if (!t.getCarId().equals("陕C·7797W")){
                list.add(t);
            }
        }
        return list;
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
        map.put("pagesize","800");
        map.put("correction", "denoise=1,mapmatch=1");
        String result = HttpClientUtil.doPost(url,map);
        JSONObject object = JSONObject.parseObject(result);
        JSONArray array = new JSONArray();
        if (object.get("errcode").toString().equals("10000")) {
            JSONObject jsonObject = object.getJSONObject("data");
            array = jsonObject.getJSONArray("tracks");
        }
        System.out.println(array);
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
