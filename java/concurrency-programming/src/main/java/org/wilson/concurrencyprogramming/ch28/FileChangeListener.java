package org.wilson.concurrencyprogramming.ch28;

public class FileChangeListener {
    @Subscribe
    public void onChnage(FileChangeEvent event) {
        System.out.printf("%s-%s\n", event.getPath(), event.getKind());
    }
}
