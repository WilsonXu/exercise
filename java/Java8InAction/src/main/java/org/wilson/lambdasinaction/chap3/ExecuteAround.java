package org.wilson.lambdasinaction.chap3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by wilson on 7/8/16.
 */
public class ExecuteAround {
    public static void main(String... args) throws IOException {
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---");

        String oneLine = processFile(BufferedReader::readLine);
        System.out.println(oneLine);

        String twoLine = processFile(br -> br.readLine() + br.readLine());
        System.out.println(twoLine);
    }

    public static String processFileLimited() throws IOException {
        String path = Objects.requireNonNull(ExecuteAround.class.getClassLoader().getResource("org/wilson/lambdasinaction/chap3/data.txt")).getPath();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        String path = Objects.requireNonNull(ExecuteAround.class.getClassLoader().getResource("org/wilson/lambdasinaction/chap3/data.txt")).getPath();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return p.process(br);
        }
    }

    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

}
