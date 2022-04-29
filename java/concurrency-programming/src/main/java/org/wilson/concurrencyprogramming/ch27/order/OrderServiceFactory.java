package org.wilson.concurrencyprogramming.ch27.order;

import org.wilson.concurrencyprogramming.ch19.Future;
import org.wilson.concurrencyprogramming.ch27.OrderService;

public class OrderServiceFactory {
    private final static ActiveMessageQueue activeMessageQueue = new ActiveMessageQueue();

    private OrderServiceFactory() {
    }

    public static OrderService toActiveObject(OrderService orderService) {
        return new OrderServiceProxy(orderService, activeMessageQueue);
    }

    public static void main(String[] args) throws InterruptedException {
        OrderService orderService = OrderServiceFactory.toActiveObject(new OrderServiceImpl());
        orderService.order("hello", 453453);
        Future<String> future = orderService.findOrderDetails(123123);
        System.out.println("Return immediately");
        System.out.println("The Order details is: " + future.get());
    }
}
