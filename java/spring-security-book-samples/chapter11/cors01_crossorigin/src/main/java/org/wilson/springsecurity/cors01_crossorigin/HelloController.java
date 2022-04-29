package org.wilson.springsecurity.cors01_crossorigin;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*", methods = {RequestMethod.POST}, maxAge = 60 * 30, exposedHeaders = {"Access-Control-Request-Headers","Access-Control-Request-Method"})
public class HelloController {
    @PostMapping("/post")
    public String post() {
        return "hello post";
    }

    @CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*", methods = {RequestMethod.OPTIONS, RequestMethod.PUT}, maxAge = 60 * 30, exposedHeaders = {"Access-Control-Max-Age"})
    @PutMapping("/put")
    public String put() {
        return "hello put";
    }
    @GetMapping("/get")
    public String get() {
        return "hello get";
    }
}
