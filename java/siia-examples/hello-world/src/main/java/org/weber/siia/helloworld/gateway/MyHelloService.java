package org.weber.siia.helloworld.gateway;

/**
 * Created by wilson on 15/4/20.
 */
public class MyHelloService implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
