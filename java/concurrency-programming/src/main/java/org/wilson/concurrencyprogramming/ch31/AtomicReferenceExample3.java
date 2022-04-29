package org.wilson.concurrencyprogramming.ch31;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class AtomicReferenceExample3 {
    private static volatile AtomicReference<DebitCard> debitCardRef = new AtomicReference<>(new DebitCard("Wilson", 0));

    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            while (true) {
                final DebitCard dc = AtomicReferenceExample3.debitCardRef.get();
                DebitCard newDC = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
                if (AtomicReferenceExample3.debitCardRef.compareAndSet(dc, newDC)) {
                    System.out.println(newDC);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "T-" + i).start());
    }
}
