package org.weber.prospringintegration.transform;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by wxu on 5/20/2015.
 */
@Component
public class CustomerConverter implements Converter<Map<String, String>, Customer>{
    @Override
    public Customer convert(Map<String, String> customerMap) {
        Customer customer = new Customer();

        customer.setFirstName(customerMap.get("firstName"));
        customer.setLastName(customerMap.get("lastName"));
        customer.setAddress(customerMap.get("address"));
        customer.setCity(customerMap.get("city"));
        customer.setState(customerMap.get("state"));
        customer.setZip(customerMap.get("zip"));
        return customer;
    }
}
