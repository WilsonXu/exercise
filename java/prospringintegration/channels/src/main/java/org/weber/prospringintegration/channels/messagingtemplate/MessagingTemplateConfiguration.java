package org.weber.prospringintegration.channels.messagingtemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.MessagingTemplate;

/**
 * Created by wxu on 5/14/2015.
 */
@Configuration
public class MessagingTemplateConfiguration {
    @Value("#{ticketChannel}")
    private MessageChannel messageChannel;

    @Bean
    public MessagingTemplate messagingTemplate() {
        MessagingTemplate messagingTemplate = new MessagingTemplate();
        messagingTemplate.setDefaultChannel(this.messageChannel);
        messagingTemplate.setSendTimeout(1000);
        return messagingTemplate;
    }
}
