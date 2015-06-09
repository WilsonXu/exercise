package org.weber.prospringintegration.messageflow.filter;

import org.springframework.integration.Message;
import org.springframework.integration.core.MessageSelector;
import org.springframework.stereotype.Component;
import org.weber.prospringintegration.messageflow.domain.MarketItem;

/**
 * Created by wxu on 5/28/2015.
 */
@Component
public class MessageSelectorStockItemFilter implements MessageSelector {
    @Override
    public boolean accept(Message<?> message) {
        MarketItem marketItem = (MarketItem) message.getPayload();
        return marketItem != null && marketItem.getType().equals("stock");
    }
}
