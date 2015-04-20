package org.weber.siia.helloworld.channel;

/**
 * Created by wxu on 4/20/2015.
 */
public class MyHelloService implements  HelloService {
    public void sayHello(String name) {
       System.out.println("Hello " + name);
    }
}
