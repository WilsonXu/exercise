package org.wilson.springsecurity.csrf1;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @PostMapping("/withdraw")
    public void withdraw() {
        System.out.println("executed a withdraw action.");
    }
}
