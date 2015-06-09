package org.weber.prospringintegration.messageflow.router;

import org.springframework.integration.Message;
import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;

/**
 * Created by wxu on 5/28/2015.
 */
@Component
public class MarketHeaderTypeRouter {
    @Router
    public String route(Message<String> message) {
        String channelId = (String) message.getHeaders().get("ITEM_TYPE");
        return channelId;
    }
}
