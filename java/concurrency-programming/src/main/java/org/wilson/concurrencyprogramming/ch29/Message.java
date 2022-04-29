package org.wilson.concurrencyprogramming.ch29;

public interface Message {
    Class<? extends Message> getType();
}
