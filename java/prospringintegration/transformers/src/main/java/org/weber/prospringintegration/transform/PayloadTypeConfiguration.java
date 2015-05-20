package org.weber.prospringintegration.transform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.transformer.PayloadTypeConvertingTransformer;

/**
 * Created by wxu on 5/20/2015.
 */
@Configuration
public class PayloadTypeConfiguration {
    @Autowired
    private CustomerConverter converter;

    @Bean
    public PayloadTypeConvertingTransformer payloadTypeConvertingTransformer() {
        PayloadTypeConvertingTransformer transformer = new PayloadTypeConvertingTransformer();
        transformer.setConverter(this.converter);
        return transformer;
    }
}
