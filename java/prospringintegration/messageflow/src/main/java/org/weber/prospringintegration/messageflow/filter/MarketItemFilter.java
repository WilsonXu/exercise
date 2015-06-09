package org.weber.prospringintegration.messageflow.filter;

import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * Created by wxu on 5/28/2015.
 */
@Component
public class MarketItemFilter {
    @Filter
    public boolean acceptViaHeader(@Header("ITEM_TYPE") String itemType) {
        return itemType.equals("stock");
    }
}
