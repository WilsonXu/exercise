package org.wilson.concurrencyprogramming.ch27.common;

import org.wilson.concurrencyprogramming.ch19.Future;
import org.wilson.concurrencyprogramming.ch19.FutureService;
import org.wilson.concurrencyprogramming.ch27.OrderService;

import java.util.concurrent.TimeUnit;

public class OrderServiceImpl implements OrderService {
    @ActiveMethod
    @Override
    public Future<String> findOrderDetails(long orderId) {
        return FutureService.<Long, String>newService().submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("Processing the orderID -> " + orderId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "The order details information";
        }, orderId);
    }

    @ActiveMethod
    @Override
    public void order(String account, long orderId) {
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("Processing the order for the account " + account + ", orderId " + orderId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
