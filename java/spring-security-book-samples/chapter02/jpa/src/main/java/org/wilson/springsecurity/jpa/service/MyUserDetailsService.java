package org.wilson.springsecurity.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.wilson.springsecurity.jpa.dao.UserDao;
import org.wilson.springsecurity.jpa.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("The user does not exist");
        }
        return user;
    }
}
