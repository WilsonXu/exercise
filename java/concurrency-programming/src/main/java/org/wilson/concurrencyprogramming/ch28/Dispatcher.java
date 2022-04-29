package org.wilson.concurrencyprogramming.ch28;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class Dispatcher {
    public static final Executor SEQ_EXECUTOR_SERVICE = SeqExecutorService.INSTANCE;
    public static final Executor PER_THREAD_EXECUTOR_SERVICE = PerThreadExecutorService.INSTANCE;

    private final Executor executorService;
    private final EventExceptionHandler exceptionHandler;

    private Dispatcher(Executor executorService, EventExceptionHandler exceptionHandler) {
        this.executorService = executorService;
        this.exceptionHandler = exceptionHandler;
    }

    public void dispatch(Bus bus, Registry registry, Object event, String topic) {
        ConcurrentLinkedQueue<Subscriber> subscribers = registry.scanSubscriber(topic);
        if (subscribers == null) {
            if (this.exceptionHandler != null) {
                this.exceptionHandler.handle(new IllegalArgumentException("The topic " + topic + " is not bound yet."),
                        new BaseEventContext(bus.getBusName(), null, event));
            }
            return;
        }
        subscribers.stream().filter(s -> !s.isDisable())
                .filter(s -> s.getSubscribeMethod().getParameterTypes()[0].isAssignableFrom(event.getClass()))
                .forEach(s -> Dispatcher.this.realInvokeSubscribe(s, event, bus));
    }

    public void close() {
        if (this.executorService instanceof ExecutorService) {
            ((ExecutorService) this.executorService).shutdown();
        }
    }

    private void realInvokeSubscribe(Subscriber subscriber, Object event, Bus bus) {
        this.executorService.execute(() -> {
            try {
                subscriber.getSubscribeMethod().invoke(subscriber.getSubscribeObject(), event);
            } catch (Exception e) {
                if (Dispatcher.this.exceptionHandler != null) {
                    Dispatcher.this.exceptionHandler.handle(e, new BaseEventContext(bus.getBusName(), subscriber, event));
                }
            }
        });
    }

    static Dispatcher newDispatcher(EventExceptionHandler exceptionHandler, Executor executor) {
        return new Dispatcher(executor, exceptionHandler);
    }

    static Dispatcher seqDispatcher(EventExceptionHandler exceptionHandler) {
        return new Dispatcher(Dispatcher.SEQ_EXECUTOR_SERVICE, exceptionHandler);
    }
    static Dispatcher perThreadDispatcher(EventExceptionHandler exceptionHandler) {
        return new Dispatcher(Dispatcher.PER_THREAD_EXECUTOR_SERVICE, exceptionHandler);
    }

    private static class SeqExecutorService implements Executor {
        private final static SeqExecutorService INSTANCE = new SeqExecutorService();

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    private static class PerThreadExecutorService implements Executor {
        private final static PerThreadExecutorService INSTANCE = new PerThreadExecutorService();

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }

    private static record BaseEventContext(String eventBusName, Subscriber subscriber, Object event) implements EventContext {
        @Override
        public String getSource() {
            return this.eventBusName;
        }

        @Override
        public Object getSubscriber() {
            return this.subscriber != null ? this.subscriber.getSubscribeObject() : null;
        }

        @Override
        public Method getSubscribe() {
            return this.subscriber != null ? this.subscriber.getSubscribeMethod() : null;
        }

        @Override
        public Object getEvent() {
            return this.getEvent();
        }
    }
}
