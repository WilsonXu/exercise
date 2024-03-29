package org.wilson.springsecurity.base_on_url_dy.mapper;

import org.wilson.springsecurity.base_on_url_dy.model.Role;
import org.wilson.springsecurity.base_on_url_dy.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<Role> getUserRoleByUid(Integer uid);
    User loadUserByUsername(String username);
}
