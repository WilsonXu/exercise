package org.weber.prospringintegration.messageflow.domain;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

/**
 * Created by wxu on 5/25/2015.
 */
@Component
public class BondRegistrar {
    @ServiceActivator
    public void registerBond(MarketItem item) {
        System.out.println("Registering bond: " + item.getDescription());
    }
}
