package org.wilson.concurrencyprogramming.ch08;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class BasicThreadPool extends Thread implements ThreadPool{

    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();
    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final int initSize;
    private final int maxSize;
    private final int coreSize;
    private final long keepAliveTime;
    private final TimeUnit timeUnit;
    private int activeCount;

    private volatile boolean isShutDown = false;

    private final ThreadFactory threadFactory;
    private final RunnableQueue runnableQueue;
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();


    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize, maxSize, coreSize, BasicThreadPool.DEFAULT_THREAD_FACTORY, queueSize,
                BasicThreadPool.DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize, ThreadFactory threadFactory, int queueSize,
                           DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    private void init() {
        this.start();
        IntStream.range(0, this.initSize).forEach(i -> this.newThread());
    }

    private void newThread() {
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        this.threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    private void removeThread() {
        ThreadTask threadTask = this.threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    @Override
    public void run() {
        while (!isShutDown && !isInterrupted()) {
            try {
                this.timeUnit.sleep(this.keepAliveTime);
            } catch (InterruptedException e) {
                this.isShutDown = true;
                break;
            }
            synchronized (this) {
                if (this.isShutDown) {
                    break;
                }
                if (this.runnableQueue.size() > 0 && this.activeCount < this.coreSize) {
                    IntStream.range(this.initSize, this.coreSize).forEach(i -> this.newThread());
                    continue;
                }
                if (this.runnableQueue.size() > 0 && this.activeCount < this.maxSize) {
                    IntStream.range(this.coreSize, this.maxSize).forEach(i -> this.newThread());
                    continue;
                }
                if (this.runnableQueue.size() == 0 && this.activeCount > this.coreSize) {
                    IntStream.range(this.coreSize, this.activeCount).forEach(i -> this.removeThread());
                    continue;
                }
            }
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (this.isShutDown) {
            throw new IllegalStateException("The thread pool is destroyed");
        }
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (this.isShutDown) {
                return;
            }
            this.isShutDown = true;
            this.threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        if (this.isShutDown) {
            throw new IllegalStateException("The thread pool is destroyed");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (this.isShutDown) {
            throw new IllegalStateException("The thread pool is destroyed");
        }
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (this.isShutDown) {
            throw new IllegalStateException("The thread pool is destroyed");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (this.isShutDown) {
            throw new IllegalStateException("The thread pool is destroyed");
        }
        return this.runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        if (this.isShutDown) {
            throw new IllegalStateException("The thread pool is destroyed");
        }
        synchronized (this) {
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return this.isShutDown;
    }

    private static class DefaultThreadFactory implements  ThreadFactory {
        private  static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
        private  static final ThreadGroup GROUP = new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndDecrement());
        private  static final AtomicInteger COUNTER= new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(DefaultThreadFactory.GROUP, runnable, "thread-pool-" + COUNTER.getAndDecrement());
        }
    }

    private static class ThreadTask {
        private Thread thread;
        private InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }

        public Thread getThread() {
            return thread;
        }

        public InternalTask getInternalTask() {
            return internalTask;
        }
    }
}
