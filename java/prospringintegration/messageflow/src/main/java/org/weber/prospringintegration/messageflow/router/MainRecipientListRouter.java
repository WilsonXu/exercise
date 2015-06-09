package org.weber.prospringintegration.messageflow.router;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.weber.prospringintegration.messageflow.domain.MarketItem;
import org.weber.prospringintegration.messageflow.domain.MarketItemCreator;

/**
 * Created by wxu on 5/28/2015.
 */
public class MainRecipientListRouter {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("router-recipientlist.xml");

        MessageChannel channel = context.getBean("marketItemChannel", MessageChannel.class);
        MarketItemCreator marketItemCreator = context.getBean("marketItemCreator", MarketItemCreator.class);

        for(MarketItem marketItem : marketItemCreator.getMarketItems()) {
            channel.send(MessageBuilder.withPayload(marketItem).build());
        }
    }
}
