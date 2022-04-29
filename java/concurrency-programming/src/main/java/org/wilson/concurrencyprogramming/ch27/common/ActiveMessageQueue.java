package org.wilson.concurrencyprogramming.ch27.common;

import java.util.LinkedList;

public class ActiveMessageQueue {
    private final LinkedList<ActiveMessage> messages = new LinkedList<>();

    public ActiveMessageQueue() {
        new ActiveDaemonThead(this).start();
    }

    public void offer(ActiveMessage activeMessage) {
        synchronized (this) {
            this.messages.addLast(activeMessage);
            this.notify();
        }
    }

    protected ActiveMessage take() {
        synchronized (this) {
            while (this.messages.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return this.messages.removeFirst();
        }
    }
}
