package org.wilson.concurrencyprogramming.ch35;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class StreamExample1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamExample1.class);

    public static void main(String[] args) {
        var stream = fromValues().map(i -> {
            StreamExample1.LOGGER.info("multiply by 2");
            return i * 2;
        });
        StreamExample1.LOGGER.info("=================");
        stream.forEach(i -> StreamExample1.LOGGER.info(String.valueOf(i)));
    }

    private static Stream<Integer> fromValues() {
        return Stream.of(1, 2, 3, 4);
    }
}
