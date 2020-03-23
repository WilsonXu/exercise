package org.wilson.corejava.v1.ch05.objectanalyzer;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * Created by wilson on 2020/3/23.
 */
@Slf4j
public class ObjectAnalyzerTest {
    public static void main(String[] args) {
        var squares = IntStream.rangeClosed(1, 5).map(i -> i * i).toArray();
        log.info(new ObjectAnalyzer().toString(squares));
    }
}
