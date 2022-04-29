package org.wilson.concurrencyprogramming.ch29;

public class Event implements Message{
    @Override
    public Class<? extends Message> getType() {
        return this.getClass();
    }
}
