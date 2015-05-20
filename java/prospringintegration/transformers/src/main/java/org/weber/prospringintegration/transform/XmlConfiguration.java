package org.weber.prospringintegration.transform;

import org.springframework.context.annotation.Bean;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.stereotype.Component;

/**
 * Created by wxu on 5/18/2015.
 */
@Component
public class XmlConfiguration {
    @Bean
    public CastorMarshaller marshaller() {
        return new CastorMarshaller();
    }
}
