package org.wilson.concurrencyprogramming.ch27.common;

import org.wilson.concurrencyprogramming.ch19.Future;
import org.wilson.concurrencyprogramming.ch27.ActiveFuture;
import org.wilson.concurrencyprogramming.ch27.OrderService;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ActiveServiceFactory {
    private final static ActiveMessageQueue activeMessageQueue = new ActiveMessageQueue();

    private ActiveServiceFactory() {
    }

    public static <T> T active(T instance) {
        return (T) Proxy.newProxyInstance(instance.getClass().getClassLoader(),
                instance.getClass().getInterfaces(), new ActiveInvocationHandler<>(instance));
    }

    private record ActiveInvocationHandler<T>(T instance) implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(ActiveMethod.class)) {
                this.checkMethod(method);
                ActiveMessage.Builder builder = new ActiveMessage.Builder().useMethod(method).withObjects(args).forService(this.instance);
                Object result = null;
                if (this.isReturnFutureType(method)) {
                    result = new ActiveFuture<>();
                    builder.returnFuture((ActiveFuture<Object>) result);
                }
                ActiveServiceFactory.activeMessageQueue.offer(builder.build());
                return result;
            } else {
                return method.invoke(this.instance, args);
            }
        }

        private void checkMethod(Method method) throws IllegalActiveMethod {
            if (!this.isReturnFutureType(method) && !this.isReturnVoidType(method)) {
                throw new IllegalActiveMethod("The method " + method.getName() + "'s return type must be void/Future");
            }
        }

        private boolean isReturnFutureType(Method method) throws IllegalActiveMethod {
            return method.getReturnType().isAssignableFrom(Future.class);
        }

        private boolean isReturnVoidType(Method method) throws IllegalActiveMethod {
            return method.getReturnType().equals(Void.TYPE);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OrderService orderService = ActiveServiceFactory.active(new OrderServiceImpl());
        orderService.order("hello", 453453);
        Future<String> future = orderService.findOrderDetails(123123);
        System.out.println("Return immediately");
        System.out.println("The Order details is: " + future.get());
    }
}
