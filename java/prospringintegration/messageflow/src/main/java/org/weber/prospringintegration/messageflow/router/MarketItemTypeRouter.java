package org.weber.prospringintegration.messageflow.router;

import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;
import org.weber.prospringintegration.messageflow.domain.MarketItem;

/**
 * Created by wxu on 5/25/2015.
 */
@Component
public class MarketItemTypeRouter {
    @Router
    public String route(MarketItem item) {
        String channelId = item.getType();
        return channelId;
    }
}
