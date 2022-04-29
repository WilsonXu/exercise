package org.wilson.concurrencyprogramming.ch27.order;

import org.wilson.concurrencyprogramming.ch27.OrderService;

import java.util.Map;

public abstract class MethodMessage {
    public static final String ORDER_ID = "orderId";

    protected final Map<String, Object> params;
    protected final OrderService orderService;

    public MethodMessage(Map<String, Object> params, OrderService orderService) {
        this.params = params;
        this.orderService = orderService;
    }

    public abstract void execute();
}
