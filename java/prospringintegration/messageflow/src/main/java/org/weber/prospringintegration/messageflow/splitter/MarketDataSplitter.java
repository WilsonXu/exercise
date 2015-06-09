package org.weber.prospringintegration.messageflow.splitter;

import org.springframework.integration.annotation.Splitter;
import org.springframework.stereotype.Component;
import org.weber.prospringintegration.messageflow.domain.Field;
import org.weber.prospringintegration.messageflow.domain.MarketItem;

import java.util.Collection;

/**
 * Created by wxu on 5/28/2015.
 */
@Component
public class MarketDataSplitter {
    @Splitter
    public Collection<Field> splitItem(MarketItem marketItem) {
    }
}
