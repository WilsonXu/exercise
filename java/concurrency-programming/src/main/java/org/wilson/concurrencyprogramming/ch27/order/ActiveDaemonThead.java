package org.wilson.concurrencyprogramming.ch27.order;

public class ActiveDaemonThead extends Thread{
    private final ActiveMessageQueue queue;

    public ActiveDaemonThead(ActiveMessageQueue queue) {
        super("ActiveDaemonThread");
        this.queue = queue;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        for ( ; ; ) {
            MethodMessage methodMessage = this.queue.take();
            methodMessage.execute();
        }
    }
}
