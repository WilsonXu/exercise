package org.weber.siia.helloworld.gateway;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wilson on 15/4/20.
 */
public class HelloWorldExample {
    public static void main(String[] args) {
        String cfg ="siia/helloworld/gateway/context.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(cfg);
        HelloService helloService = context.getBean("helloGateway", HelloService.class);
        System.out.println(helloService.sayHello("World"));
    }
}
