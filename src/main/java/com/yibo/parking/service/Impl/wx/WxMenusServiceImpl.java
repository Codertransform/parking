package com.yibo.parking.service.Impl.wx;

import com.yibo.parking.dao.wx.WxMenuMapper;
import com.yibo.parking.entity.wx.Menu;
import com.yibo.parking.service.WxMenuService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int save(Menu menu) {
        if (menu.getId() != null){
            return menuMapper.update(menu);
        }else {
            menu.setId(EntityIdGenerate.generateId());
            return menuMapper.insert(menu);
        }
    }

    @Override
    public int delete(Menu menu) {
        return menuMapper.delete(menu);
    }
}
