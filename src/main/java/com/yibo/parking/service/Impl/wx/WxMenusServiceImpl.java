package com.yibo.parking.service.Impl.wx;

import com.yibo.parking.dao.wx.WxMenuMapper;
import com.yibo.parking.entity.wx.Menu;
import com.yibo.parking.service.WxMenuService;
import com.yibo.parking.utils.EntityIdGenerate;
import com.yibo.parking.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public Map<String, String> upload(MultipartFile icon) {
        return UploadUtil.upload(icon,"wx/icon");
    }
}
