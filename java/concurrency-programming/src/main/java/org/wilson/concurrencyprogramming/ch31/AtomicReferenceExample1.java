package org.wilson.concurrencyprogramming.ch31;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class AtomicReferenceExample1 {
    private static volatile DebitCard debitCard = new DebitCard("Wilson", 0);

    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            while (true) {
                final var dc = AtomicReferenceExample1.debitCard;
                var newDC = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
                System.out.println(newDC);
                AtomicReferenceExample1.debitCard = newDC;
                try {
                    TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "T-" + i).start());
    }
}
