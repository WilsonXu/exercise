package org.wilson.springsecurity.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wilson.springsecurity.jpa.model.User;

public interface UserDao extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
