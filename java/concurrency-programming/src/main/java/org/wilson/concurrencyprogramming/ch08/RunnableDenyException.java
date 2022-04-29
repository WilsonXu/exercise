package org.wilson.concurrencyprogramming.ch08;

public class RunnableDenyException extends RuntimeException{

    public RunnableDenyException(String message) {
        super(message);
    }
}
