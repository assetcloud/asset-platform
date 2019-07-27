package com.asset.service;

import com.asset.bean.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface IMenuService extends IService<Menu> {

    Menu getMenus(String userId);

    Menu getFactoryMenus(String userId);
}
