package org.weber.prospringintegration.transform;

import org.springframework.integration.Message;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by wxu on 5/20/2015.
 */
@Component
public class HeaderMapper {
    @Transformer
    public Message<Customer> map(Message<Map<String, String>> message) {

        Map<String, String> mapMessage = message.getPayload();
        Customer customer = new Customer();

        customer.setFirstName(mapMessage.get("firstName"));
        customer.setLastName(mapMessage.get("lastName"));
        customer.setAddress(mapMessage.get("address"));
        customer.setCity(mapMessage.get("city"));
        customer.setState(mapMessage.get("state"));
        customer.setZip(mapMessage.get("zip"));

        return MessageBuilder.withPayload(customer)
                .copyHeadersIfAbsent(message.getHeaders())
                .setHeaderIfAbsent("count", 12).build();
    }
}
