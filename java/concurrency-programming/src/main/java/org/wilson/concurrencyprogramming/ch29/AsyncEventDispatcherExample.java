package org.wilson.concurrencyprogramming.ch29;

import java.util.concurrent.TimeUnit;

public class AsyncEventDispatcherExample {
    public static void main(String[] args) {
        var dispatcher = new AsyncEventDispatcher();
        dispatcher.registerChannel(EventDispatchExample.InputEvent.class, new AsyncInputEventHandler(dispatcher));
        dispatcher.registerChannel(EventDispatchExample.ResultEvent.class, new AsyncResultEventHandler());
        dispatcher.dispatch(new EventDispatchExample.InputEvent(1, 2));
    }
    static class AsyncResultEventHandler extends AsyncChannel {
        @Override
        protected void handle(Event message) {
            var resultEventEvent = (EventDispatchExample.ResultEvent) message;
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The result is " + resultEventEvent.getResult());
        }
    }

    static class AsyncInputEventHandler extends AsyncChannel {
        private final AsyncEventDispatcher dispatcher;

        public AsyncInputEventHandler(AsyncEventDispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        @Override
        protected void handle(Event message) {
            var inputEvent = (EventDispatchExample.InputEvent) message;
            System.out.printf("X: %d, Y: %d\n", inputEvent.getX(), inputEvent.getY());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            var result = inputEvent.getX() + inputEvent.getY();
            dispatcher.dispatch(new EventDispatchExample.ResultEvent(result));
        }
    }
}
