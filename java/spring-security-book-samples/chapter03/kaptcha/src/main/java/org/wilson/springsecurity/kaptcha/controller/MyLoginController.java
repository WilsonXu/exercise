package org.wilson.springsecurity.kaptcha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyLoginController {
    @RequestMapping("/mylogin.html")
    public String myLogin() {
        return "mylogin";
    }
}
