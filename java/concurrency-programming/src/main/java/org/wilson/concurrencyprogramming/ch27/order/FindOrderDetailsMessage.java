package org.wilson.concurrencyprogramming.ch27.order;

import org.wilson.concurrencyprogramming.ch19.Future;
import org.wilson.concurrencyprogramming.ch27.ActiveFuture;
import org.wilson.concurrencyprogramming.ch27.OrderService;

import java.util.Map;

public class FindOrderDetailsMessage extends MethodMessage {
    public static final String ACTIVE_FUTURE = "activeFuture";

    public FindOrderDetailsMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        Future<String> realFuture = this.orderService.findOrderDetails((Long) params.get(MethodMessage.ORDER_ID));
        ActiveFuture activeFuture = (ActiveFuture) params.get(FindOrderDetailsMessage.ACTIVE_FUTURE);
        try {
            String result = realFuture.get();
            activeFuture.finish(result);
        } catch (InterruptedException e) {
            activeFuture.finish(null);
        }
    }
}
