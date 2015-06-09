package org.weber.prospringintegration.messageflow.filter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.weber.prospringintegration.messageflow.domain.MarketItem;
import org.weber.prospringintegration.messageflow.domain.MarketItemCreator;

/**
 * Created by wxu on 5/28/2015.
 */
public class MainFilterDynamic {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("filter-dynamic.xml");

        MessageChannel channel = context.getBean("marketItemChannel", MessageChannel.class);
        MarketItemCreator marketItemCreator = context.getBean("marketItemCreator", MarketItemCreator.class);

        for (MarketItem marketItem : marketItemCreator.getMarketItems()) {
            channel.send(MessageBuilder.withPayload(marketItem) .setHeader("ITEM_TYPE", marketItem.getType()).build());
        }
    }
}
