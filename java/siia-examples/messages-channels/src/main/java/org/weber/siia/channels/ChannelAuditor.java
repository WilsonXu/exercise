package org.weber.siia.channels;

import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.channel.ChannelInterceptor;
import org.springframework.integration.channel.interceptor.ChannelInterceptorAdapter;

/**
 * Created by wxu on 4/24/2015.
 */
public class ChannelAuditor extends ChannelInterceptorAdapter {
    private AuditService auditService;

    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        this.auditService.audit(message.getPayload());
        return  message;
    }
}
