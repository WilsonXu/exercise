package org.wilson.springsecurity.jpa.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserController {
    @GetMapping("/user")
    public void userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println("name = " + name);
        System.out.println("authorities = " + authorities);
        new Thread(() -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null) {
                System.out.println("Fetcing the user info is failed");
                return;
            }
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ":name = " + auth.getName());
            System.out.println(threadName + ":authorities = " + auth.getAuthorities());
        }).start();
    }
}
