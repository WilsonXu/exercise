package org.weber.prospringintegration.transform;

import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.Headers;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by wxu on 5/20/2015.
 */
@Component
public class HeaderInterceptor {
    @Transformer
    public Customer map(Map<String, String> message, @Header int count, @Headers Map<String, Object> headerMap) {
        Customer customer = new Customer();
        customer.setFirstName(message.get("firstName"));
        customer.setLastName(message.get("lastName"));
        customer.setAddress(message.get("address"));
        customer.setCity(message.get("city"));
        customer.setState(message.get("state"));
        customer.setZip(message.get("zip"));

        System.out.println("Count:" + count);
        for (String key : headerMap.keySet()) {
            System.out.println("Key: " + key + "  Value: " + headerMap.get(key));
        }

        return customer;
    }
}
