package org.wilson.concurrencyprogramming.ch27.order;

import org.wilson.concurrencyprogramming.ch19.Future;
import org.wilson.concurrencyprogramming.ch27.ActiveFuture;
import org.wilson.concurrencyprogramming.ch27.OrderService;

import java.util.HashMap;
import java.util.Map;

public class OrderServiceProxy implements OrderService {
    private final OrderService orderService;
    private final ActiveMessageQueue activeMessageQueue;

    public OrderServiceProxy(OrderService orderService, ActiveMessageQueue activeMessageQueue) {
        this.orderService = orderService;
        this.activeMessageQueue = activeMessageQueue;
    }

    @Override
    public Future<String> findOrderDetails(long orderId) {
        final ActiveFuture<String> activeFuture = new ActiveFuture<>();
        Map<String ,Object> params = new HashMap<>();
        params.put(MethodMessage.ORDER_ID, orderId);
        params.put(FindOrderDetailsMessage.ACTIVE_FUTURE, activeFuture);
        MethodMessage message = new FindOrderDetailsMessage(params, this.orderService);
        this.activeMessageQueue.offer(message);
        return activeFuture;
    }

    @Override
    public void order(String account, long orderId) {
        Map<String ,Object> params = new HashMap<>();
        params.put(OrderMessage.ACCOUNT, account);
        params.put(MethodMessage.ORDER_ID, orderId);
        MethodMessage message = new OrderMessage(params, this.orderService);
        this.activeMessageQueue.offer(message);
    }
}
