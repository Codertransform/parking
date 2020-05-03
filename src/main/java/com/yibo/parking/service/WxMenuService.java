package com.yibo.parking.service;

import com.yibo.parking.entity.wx.Menu;

import java.util.List;

public interface WxMenuService {

    List<Menu> getMenus(Menu menu);

    Menu get(Menu menu);

    int save(Menu menu);

    int delete(Menu menu);
}
