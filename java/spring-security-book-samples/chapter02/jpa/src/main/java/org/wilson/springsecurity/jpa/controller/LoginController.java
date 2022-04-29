package org.wilson.springsecurity.jpa.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class LoginController {
    @RequestMapping("/index")
    public String index() {
        return "login success";
    }
    @RequestMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

    @RequestMapping("/authentication")
    public  void authentication(Authentication authentication) {
        System.out.println("authentication = " + authentication);
    }

    @RequestMapping("/principal")
    public  void authentication(Principal principal, HttpServletRequest req) {
        System.out.println("req.getClass() = " + req.getClass());
        System.out.println("principal = " + principal);
    }

    @RequestMapping("/info")
    public void info(HttpServletRequest req) {
        System.out.println("remoteUser = " + req.getRemoteUser());
        System.out.println("auth.getName = " + ((Authentication) req.getUserPrincipal()).getName());
        System.out.println("admin = " + req.isUserInRole("admin"));
    }
}
