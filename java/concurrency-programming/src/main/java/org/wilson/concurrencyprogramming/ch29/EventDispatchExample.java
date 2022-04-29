package org.wilson.concurrencyprogramming.ch29;

public class EventDispatchExample {
    public static void main(String[] args) {
        var dispatcher = new EventDispatcher();
        dispatcher.registerChannel(InputEvent.class, new InputEventHandler(dispatcher));
        dispatcher.registerChannel(ResultEvent.class, new ResultEventHandler());
        dispatcher.dispatch(new InputEvent(1, 2));
    }

    static class InputEvent extends Event {
        private final int x;
        private final int y;

        public InputEvent(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    static class ResultEvent extends Event {
        private final int result;

        public ResultEvent(int result) {
            this.result = result;
        }

        public int getResult() {
            return result;
        }
    }

    static class ResultEventHandler implements Channel<ResultEvent> {
        @Override
        public void dispatch(ResultEvent message) {
            System.out.println("The result is " + message.getResult());
        }
    }

    record InputEventHandler(
            EventDispatcher dispatcher) implements Channel<InputEvent> {

        @Override
        public void dispatch(InputEvent message) {
            System.out.printf("X: %d, Y: %d\n", message.getX(), message.getY());
            var result = message.getX() + message.getY();
            dispatcher.dispatch(new ResultEvent(result));
        }
    }
}
