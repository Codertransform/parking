package com.yibo.parking.dao.wx;

import com.yibo.parking.entity.wx.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WxMenuMapper {

    List<Menu> getMenus(Menu menu);

    Menu get(Menu menu);

    int insert(Menu menu);

    int update(Menu menu);

    int delete(Menu menu);

    int status(Menu menu);

    List<Menu> findByStatus(Menu menu);
}
