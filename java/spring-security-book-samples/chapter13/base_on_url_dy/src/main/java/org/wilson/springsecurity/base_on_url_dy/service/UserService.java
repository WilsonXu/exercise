package org.wilson.springsecurity.base_on_url_dy.service;

import org.wilson.springsecurity.base_on_url_dy.mapper.UserMapper;
import org.wilson.springsecurity.base_on_url_dy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userMapper.loadUserByUsername(username);
        if (user == null) {
            throw  new UsernameNotFoundException("The use does not exist");
        }
        user.setRoles(this.userMapper.getUserRoleByUid(user.getId()));
        return user;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
