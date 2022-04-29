package org.wilson.concurrencyprogramming.ch27;

import org.wilson.concurrencyprogramming.ch19.Future;

public interface OrderService {
    Future<String> findOrderDetails(long orderId);

    void order(String account, long orderId);
}
