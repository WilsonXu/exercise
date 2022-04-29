package org.wilson.concurrencyprogramming.ch32;

import com.google.common.util.concurrent.Monitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuavaMonitorExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaMonitorExample.class);

    private static final Monitor monitor = new Monitor();
    private static int x =0;
    private static final int MAX_VALUE = 10;
    private static final Monitor.Guard INC_WHEN_LESS_10 = new Monitor.Guard(GuavaMonitorExample.monitor) {
        @Override
        public boolean isSatisfied() {
            return x < GuavaMonitorExample.MAX_VALUE;
        }
    };

    public static void main(String[] args) throws InterruptedException {
        //noinspection InfiniteLoopStatement
        while (true) {
            GuavaMonitorExample.monitor.enterWhen(GuavaMonitorExample.INC_WHEN_LESS_10);
            try {
                GuavaMonitorExample.x ++;
                if (GuavaMonitorExample.LOGGER.isInfoEnabled()) {
                    GuavaMonitorExample.LOGGER.info("{}: x value is: {}", Thread.currentThread(), GuavaMonitorExample.x);
                }
            } finally {
                GuavaMonitorExample.monitor.leave();
            }
        }
    }
}
