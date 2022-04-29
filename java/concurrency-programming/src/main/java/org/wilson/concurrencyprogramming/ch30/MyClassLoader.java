package org.wilson.concurrencyprogramming.ch30;

import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader extends URLClassLoader {
    private final byte[] bytes;

    public MyClassLoader(byte[] bytes) {
        super(new URL[0], ClassLoader.getSystemClassLoader());
        this.bytes = bytes;
    }

    @Override
    protected Class<?> findClass(String name) {
        return this.defineClass(name, this.bytes, 0, this.bytes.length);
    }
}
