package org.wilson.concurrencyprogramming.ch29;

public interface Channel<E extends Message> {
    void dispatch(E message);
}
