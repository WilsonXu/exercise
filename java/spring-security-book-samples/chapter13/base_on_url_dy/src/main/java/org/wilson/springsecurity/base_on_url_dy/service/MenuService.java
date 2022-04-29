package org.wilson.springsecurity.base_on_url_dy.service;

import org.wilson.springsecurity.base_on_url_dy.mapper.MenuMapper;
import org.wilson.springsecurity.base_on_url_dy.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private MenuMapper menuMapper;

    public List<Menu> getAllMenu() {
        return this.menuMapper.getAllMenu();
    }

    @Autowired
    public void setMenuMapper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }
}
