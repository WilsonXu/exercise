package org.wilson.concurrencyprogramming.ch28;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DirectoryTargetMonitor {
    private WatchService watchService;
    private final Bus eventBus;
    private final Path path;
    private volatile boolean start = false;

    public DirectoryTargetMonitor(final Bus eventBus, String targetPath) {
        this(eventBus, targetPath, "");
    }

    public DirectoryTargetMonitor(final Bus eventBus, String targetPath, final String... morePaths) {
        this.eventBus = eventBus;
        this.path = Paths.get(targetPath, morePaths);
    }

    public void startMonitor() throws Exception {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.path.register(this.watchService, StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_CREATE);
        System.out.printf("Monitoring The directory [%s]...\n", this.path);
        this.start = true;
        while (this.start) {
            WatchKey watchKey = null;
            try {
                watchKey = this.watchService.take();
                watchKey.pollEvents().forEach(event -> {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path path = (Path) event.context();
                    Path child = DirectoryTargetMonitor.this.path.resolve(path);
                    DirectoryTargetMonitor.this.eventBus.post(new FileChangeEvent(child, kind));
                });
            } catch (Exception e) {
                this.start = false;
            } finally {
                if (watchKey != null) {
                         watchKey.reset();
                }
            }
        }
    }

    public void stopMonitor() throws Exception {
        System.out.printf("Stopping to monitor the directory [%s]...\n", this.path);
        Thread.currentThread().interrupt();
        this.start = false;
        this.watchService.close();
        System.out.printf("Stopped to monitor the directory [%s]...\n",  this.path);
    }

    public static void main(String[] args) throws Exception {
        final Bus eventBus = new AsyncEventBus((ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
        eventBus.register(new FileChangeListener());
        new DirectoryTargetMonitor(eventBus, "/Users/wilson").startMonitor();
    }
}
