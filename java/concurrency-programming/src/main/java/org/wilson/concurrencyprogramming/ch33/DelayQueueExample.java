package org.wilson.concurrencyprogramming.ch33;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {
    public static void main(String[] args) throws InterruptedException {
        final var delayQueue = new DelayQueue<DelayedEntry>();
        delayQueue.put(new DelayedEntry("A", 10 * 1000L));
        delayQueue.put(new DelayedEntry("B", 5 * 1000L));

        final var timestamp = System.currentTimeMillis();
        assert delayQueue.poll() == null;

        DelayedEntry value = delayQueue.take();
        assert value.getValue().equals("B");
        assert System.currentTimeMillis() - timestamp >= 5_000;

        value = delayQueue.take();
        assert value.getValue().equals("A");
        assert System.currentTimeMillis() - timestamp >= 10_000;
    }
    private static class DelayedEntry implements Delayed {
        private final String value;
        private final long time;

        public DelayedEntry(String value, long delayTime) {
            this.value = value;
            this.time = delayTime + System.currentTimeMillis();
        }

        public String getValue() {
            return this.value;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long delta = time - System.currentTimeMillis();
            return unit.convert(delta, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.time, ((DelayedEntry) o).time);
        }

        @Override
        public String toString() {
            return "DelayedEntry{" +
                    "value='" + value + '\'' +
                    ", time=" + time +
                    '}';
        }
    }
}
