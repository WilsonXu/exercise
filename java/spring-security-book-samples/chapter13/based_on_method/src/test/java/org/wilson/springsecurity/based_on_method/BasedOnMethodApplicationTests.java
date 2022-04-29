package org.wilson.springsecurity.based_on_method;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BasedOnMethodApplicationTests {
    @Autowired
    private HelloService helloService;

    @Test
    @WithMockUser(roles = "ADMIN", username = "wilson")
    public void preauthorizeTest01() {
        String hello = helloService.hello();
        Assertions.assertNotNull(hello);
        Assertions.assertEquals("hello", hello);
    }

    @Test
    @WithMockUser(username = "wilson")
    public void preauthorizeTest02() {
        String hello = helloService.hello("wilson");
        Assertions.assertNotNull(hello);
        Assertions.assertEquals("hello:wilson", hello);
    }

    @Test
    @WithMockUser(username = "bella")
    public void preFilterTest01() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User(i, "wilson:" + i));
        }
        helloService.addUsers(users, 99);
    }

    @Test
    @WithMockUser(username = "bella")
    public void postAuthorizeTest01() {
        User user = helloService.getUserById(1);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(1,user.getId());
        Assertions.assertEquals("wilson",user.getUsername());
    }

    @Test
    @WithMockUser(username = "bella")
    public void postFilterTest01() {
        List<User> all = helloService.getAll();
        Assertions.assertNotNull(all);
        Assertions.assertEquals(5, all.size());
        Assertions.assertEquals(2,all.get(1).getId());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void securedTest01() {
        User user = helloService.getUserByUsername("wilson");
        Assertions.assertNotNull(user);
        Assertions.assertEquals(99,user.getId());
        Assertions.assertEquals("wilson", user.getUsername());
    }

    @Test
    @WithMockUser(authorities = {"READ_USER"})
    public void securedTest02() {
        User user = helloService.getUserByUsername2("wilson");
        Assertions.assertNotNull(user);
        Assertions.assertEquals(99,user.getId());
        Assertions.assertEquals("wilson", user.getUsername());
    }

    @Test
    @WithMockUser(username = "wilson")
    public void denyAllTest01() {
        helloService.denyAll();
    }

    @Test
    @WithMockUser(username = "wilson")
    public void permitAllTest01() {
        String s = helloService.permitAll();
        Assertions.assertNotNull(s);
        Assertions.assertEquals("PermitAll", s);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void rolesAllowedTest01() {
        String s = helloService.rolesAllowed();
        Assertions.assertNotNull(s);
        Assertions.assertEquals("RolesAllowed", s);
    }

}
