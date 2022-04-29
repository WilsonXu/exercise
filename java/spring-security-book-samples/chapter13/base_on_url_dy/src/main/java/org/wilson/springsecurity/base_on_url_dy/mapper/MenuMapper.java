package org.wilson.springsecurity.base_on_url_dy.mapper;

import org.wilson.springsecurity.base_on_url_dy.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> getAllMenu();
}
