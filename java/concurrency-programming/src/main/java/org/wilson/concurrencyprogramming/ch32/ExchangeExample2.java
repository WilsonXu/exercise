package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ExchangeExample2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeExample2.class);

    public static void main(String[] args) throws InterruptedException {
        final var exchanger = new Exchanger<String>();
        var generator = new StringGenerator(exchanger, "Generator");
        var consumer = new StringConsumer(exchanger, "Consumer");
        consumer.start();
        generator.start();

        TimeUnit.MINUTES.sleep(1);
        consumer.close();
        generator.close();
    }

    private interface Closable {
        void close();

        boolean closed();
    }

    private abstract static class ClosableThread extends Thread implements Closable {
        protected final Exchanger<String> exchanger;
        private volatile boolean closed = false;

        private ClosableThread(Exchanger<String> exchanger, final String name) {
            super(name);
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (!this.closed) {
                this.doExchange();
            }
        }

        protected abstract void doExchange();

        @Override
        public void close() {
            if (ExchangeExample2.LOGGER.isInfoEnabled()) {
                ExchangeExample2.LOGGER.info("{} will be closed.", Thread.currentThread());
            }
            this.closed = true;
            this.interrupt();
        }

        @Override
        public boolean closed() {
            return this.closed || this.isInterrupted();
        }
    }

    private static class StringGenerator extends ClosableThread {
        private char initialChar = 'A';

        private StringGenerator(Exchanger<String> exchanger, String name) {
            super(exchanger, name);
        }

        @Override
        protected void doExchange() {
            var stringBuilder = new StringBuilder();
            try {
                for (int i = 0; i < 3; i++) {
                    this.randomSleep();
                    stringBuilder.append(this.initialChar++);
                }

                if (!this.closed()) {
                    this.exchanger.exchange(stringBuilder.toString());
                }
            } catch (InterruptedException e) {
                if (ExchangeExample2.LOGGER.isInfoEnabled()) {
                    ExchangeExample2.LOGGER.info("{} received the close signal", Thread.currentThread());
                }
                Thread.currentThread().interrupt();
            }
        }

        private void randomSleep() throws InterruptedException {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        }
    }

    private static class StringConsumer extends ClosableThread {
        private StringConsumer(Exchanger<String> exchanger, String name) {
            super(exchanger, name);
        }

        @Override
        protected void doExchange() {
            try {
                if (!this.closed()) {
                    var data = this.exchanger.exchange(null);
                    ExchangeExample2.LOGGER.info("{} received the data: {}", Thread.currentThread(), data);
                }
            } catch (InterruptedException e) {
                if (ExchangeExample2.LOGGER.isInfoEnabled()) {
                    ExchangeExample2.LOGGER.info("{} received the close signal", Thread.currentThread());
                }
                Thread.currentThread().interrupt();
            }
        }
    }
}
