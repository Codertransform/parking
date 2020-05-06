package com.yibo.parking.service;

import com.yibo.parking.entity.wx.Menu;

import java.util.List;
import java.util.Map;

public interface WxMenuService {

    List<Menu> getMenus(Menu menu);

    Menu get(Menu menu);

    Map<String,Object> save(Menu menu);

    int delete(Menu menu);
}
