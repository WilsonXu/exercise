package org.wilson.concurrencyprogramming.ch27.order;

import java.util.LinkedList;

public class ActiveMessageQueue {
    private final LinkedList<MethodMessage> messages = new LinkedList<>();

    public ActiveMessageQueue() {
        new ActiveDaemonThead(this).start();
    }

    public void offer(MethodMessage methodMessage) {
        synchronized (this) {
            this.messages.addLast(methodMessage);
            this.notify();
        }
    }

    protected MethodMessage take() {
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
