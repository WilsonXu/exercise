package org.weber.prospringintegration.transform;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.core.PollableChannel;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;

/**
 * Created by wxu on 5/19/2015.
 */
public class IntegrationTransformer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:integration-transformer.xml");
        JmsTemplate jmsTemplate = context.getBean("jmsTemplate", JmsTemplate.class);

        jmsTemplate.send(new MessageCreator() {
            @Override
            public javax.jms.Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("firstName", "John");
                message.setString("lastName", "Smith");
                message.setString("address", "100 State Street");
                message.setString("city", "Los Angeles");
                message.setString("state", "CA");
                message.setString("zip", "90064");
                System.out.println("Sending message: " + message);
                return message;
            }
        });

        PollableChannel output = (PollableChannel) context.getBean("output");
        Message<?> reply = output.receive();
        System.out.println("received: " + reply.getPayload());
    }
}
