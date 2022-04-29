package org.wilson.concurrencyprogramming.ch23;

public class WaitTimoutException extends Exception{
    public WaitTimoutException(String message) {
        super(message);
    }
}
