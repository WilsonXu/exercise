package org.wilson.concurrencyprogramming.ch28;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Registry {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>> subscriberContainer = new ConcurrentHashMap<>();

    public void bind(Object subscriber) {
        this.getSubscribeMethods(subscriber).forEach(m -> Registry.this.tireSubscriber(subscriber, m));
    }

    public void unbind(Object subscriber) {
        this.subscriberContainer.forEach((k, v) -> v.forEach(s -> {
            if (s.getSubscribeObject() == subscriber) {
                s.setDisable(true);
            }
        }));
    }

    public ConcurrentLinkedQueue<Subscriber> scanSubscriber(final String topic) {
        return this.subscriberContainer.get(topic);
    }

    private List<Method> getSubscribeMethods(Object subscriber) {
        final List<Method> methods = new ArrayList<>();
        Class<?> temp = subscriber.getClass();
        while (temp != null) {
            Arrays.stream(temp.getDeclaredMethods()).
                    filter(m -> m.isAnnotationPresent(Subscribe.class)
                            && m.getParameterCount() == 1
                            && m.getModifiers() == Modifier.PUBLIC)
                    .forEach(methods::add);
            temp = temp.getSuperclass();
        }
        return methods;
    }

    private void tireSubscriber(Object subscriber, Method method) {
        String topic = method.getDeclaredAnnotation(Subscribe.class).topic();
        this.subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
        this.subscriberContainer.get(topic).add(new Subscriber(subscriber, method));
    }
}
