package org.weber.prospringintegration.transform;

import org.springframework.stereotype.Component;
import org.springframework.integration.annotation.Transformer;

import java.util.Map;

/**
 * Created by wxu on 5/18/2015.
 */
@Component
public class Mapper {
    @Transformer
    public Customer map(Map<String, String> message) {
        Customer customer = new Customer();
        customer.setFirstName(message.get("firstName"));
        customer.setLastName(message.get("lastName"));
        customer.setAddress(message.get("address"));
        customer.setCity(message.get("city"));
        customer.setState(message.get("state"));
        customer.setZip(message.get("zip"));
        return customer;
    }
}
