package org.wilson.sia.ch02.taco.controller;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by wilson on 2020/3/27.
 */
@Controller
public class HomeController implements EnvironmentAware {
    @GetMapping("/")
    public String home() {
        System.out.println("-------------");
        return "home";
    }

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
