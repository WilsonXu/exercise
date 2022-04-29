package org.wilson.concurrencyprogramming.ch27.order;

import org.wilson.concurrencyprogramming.ch27.OrderService;

import java.util.Map;

public class OrderMessage extends MethodMessage {
    public static final String ACCOUNT = "account";

    public OrderMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        String account = (String) this.params.get(OrderMessage.ACCOUNT);
        long orderId = (long) this.params.get(MethodMessage.ORDER_ID);

        this.orderService.order(account, orderId);
    }
}
