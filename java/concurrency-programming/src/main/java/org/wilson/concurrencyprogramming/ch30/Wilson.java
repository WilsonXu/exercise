package org.wilson.concurrencyprogramming.ch30;

public class Wilson {
    private String name = "Wilson Xu";
    @SuppressWarnings("FieldCanBeLocal")
    private final int age = 40;
    @SuppressWarnings("unused")
    private final byte[] data = new byte[1024 * 10];

    @Override
    public String toString() {
        return "Wilson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
