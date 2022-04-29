package org.wilson.concurrencyprogramming.ch23;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ProgrammerTravel extends Thread{
    private final Latch latch;
    private final String programmer;
    private final String transportation;

    public ProgrammerTravel(Latch latch, String programmer, String transportation) {
        this.latch = latch;
        this.programmer = programmer;
        this.transportation = transportation;
    }

    @Override
    public void run() {
        System.out.println(this.programmer + " starts to take the transportation [" + this.transportation + "]");
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.programmer + " has arrived by " + this.transportation);
        this.latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        Latch latch = new CountDownLatch(4);
        new ProgrammerTravel(latch, "Alex", "Bus").start();
        new ProgrammerTravel(latch, "Gavin", "Car").start();
        new ProgrammerTravel(latch, "Jack", "Subway").start();
        new ProgrammerTravel(latch, "Dillon", "Bicycle").start();
        try {
            latch.await(TimeUnit.SECONDS, 5);
            System.out.println("== All of programmers arrived ===");
        } catch (WaitTimoutException e) {
            e.printStackTrace();
        }
    }
}
