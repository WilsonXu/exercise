package org.wilson.concurrencyprogramming.ch35;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class StreamExample2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamExample2.class);

    public static void main(String[] args) {
        var stream = fromValues().map(i -> {
            StreamExample2.LOGGER.info("multiply by 2");
            return i * 2;
        });
        StreamExample2.LOGGER.info("=================");
        stream.forEach(i -> StreamExample2.LOGGER.info(String.valueOf(i)));
    }
    
    private static Stream<Integer> fromValues() {
        return Stream.<Integer>builder().add(1).add(2).add(3).add(4).build();
    }
}
