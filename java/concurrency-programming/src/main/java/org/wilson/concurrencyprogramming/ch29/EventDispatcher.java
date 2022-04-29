package org.wilson.concurrencyprogramming.ch29;

import java.util.HashMap;
import java.util.Map;

public class EventDispatcher implements DynamicRouter<Message> {
    private final Map<Class<? extends Message>, Channel> routerTable;

    public EventDispatcher() {
        this.routerTable = new HashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Message> messageType, Channel<? extends Message> channel) {
        this.routerTable.put(messageType, channel);
    }

    @Override
    public void dispatch(Message message) {
        if (this.routerTable.containsKey(message.getType())) {
            this.routerTable.get(message.getType()).dispatch(message);
        } else {
            throw new MessageMatcherException("Can't match any channel for [" + message.getType() + "] type");
        }
    }
}
