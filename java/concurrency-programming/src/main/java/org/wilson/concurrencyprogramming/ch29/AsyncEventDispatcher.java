package org.wilson.concurrencyprogramming.ch29;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AsyncEventDispatcher implements DynamicRouter<Event>{
    private final Map<Class<? extends Event>, AsyncChannel> routerTable;

    public AsyncEventDispatcher() {
        this.routerTable = new ConcurrentHashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Event> messageType, Channel<? extends Event> channel) {
        if (!(channel instanceof AsyncChannel)) {
            throw new IllegalStateException("The channel must be AsyncChannel Type.");
        }
        this.routerTable.put(messageType, (AsyncChannel) channel);
    }

    @Override
    public void dispatch(Event message) {
        if (this.routerTable.containsKey(message.getType())) {
            this.routerTable.get(message.getType()).dispatch(message);
        } else {
            throw new MessageMatcherException("Can't match any channel for [" + message.getType() + "] type");
        }
    }

    public void shutdow() {
        this.routerTable.values().forEach(AsyncChannel::stop);
    }
}
