package org.wilson.concurrencyprogramming.ch01;

import java.util.stream.IntStream;

public class TicketWindowRunnable implements Runnable{
    private static final int MAX = 50;

    private int index = 1;

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println("The current number of the window " + Thread.currentThread().getName() + " is: " + (index ++));
        }
    }

    public static void main(String[] args) {
        final Runnable task = new TicketWindowRunnable();
        IntStream.rangeClosed(1, 4).boxed().map(i -> new Thread(task, "No. " + i)).forEach(Thread::start);
    }
}
