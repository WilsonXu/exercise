package org.wilson.springsecurity.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyLoginController {
    @RequestMapping("/mylogin.html")
    public String mylogin() {
        return "mylogin";
    }
}
