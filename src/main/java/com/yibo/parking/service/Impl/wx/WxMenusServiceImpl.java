package com.yibo.parking.service.Impl.wx;

import com.alibaba.fastjson.JSONArray;
import com.yibo.parking.dao.wx.WxMenuMapper;
import com.yibo.parking.entity.wx.Menu;
import com.yibo.parking.service.WxMenuService;
import com.yibo.parking.utils.EntityIdGenerate;
import com.yibo.parking.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxMenusServiceImpl implements WxMenuService {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Autowired
    private WxMenuMapper menuMapper;

    @Override
    public List<Menu> getMenus(Menu menu) {
        return menuMapper.getMenus(menu);
    }

    @Override
    public Menu get(Menu menu) {
        return menuMapper.get(menu);
    }

    @Override
    public Map<String,Object> save(Menu menu) {
        Map<String,Object> map = new HashMap<>();
        if (menu.getId() != null){
            int u = menuMapper.update(menu);
            map.put("code",u);
            map.put("message","修改成功");
            return map;
        }else {
            menu.setId(EntityIdGenerate.generateId());
            menu.setStatus("0");
            int i = menuMapper.insert(menu);
            map.put("code",i);
            map.put("message","添加成功");
            return map;
        }
    }

    @Override
    public int delete(Menu menu) {
        return menuMapper.delete(menu);
    }

    public Map<String, String> upload(MultipartFile icon) {
        return UploadUtil.upload(icon,"wx/icon");
    }

    public Map<String,Object> status(Menu menu) {
        Map<String,Object> map = new HashMap<>();
        int s = menuMapper.status(menu);
        if (menu.getStatus().equals("0") && s != 0){
            map.put("code",0);
            map.put("message","发布成功");
        }else {
            map.put("code",-1);
            map.put("message","下架成功");
        }
        return map;
    }

    public String findByStatusApi(){
        Menu menu  = new Menu();
        menu.setStatus("0");
        List<Menu> menus =menuMapper.findByStatus(menu);
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (Menu m : menus) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",m.getId());
            map.put("icon","https://wzytest.com" + m.getIcon());
            map.put("name",m.getName());
            map.put("link",m.getLink());
            map.put("status",m.getStatus());
            map.put("remarks",m.getRemarks());
            mapList.add(map);
        }
        return JSONArray.toJSONString(mapList);
    }
}
