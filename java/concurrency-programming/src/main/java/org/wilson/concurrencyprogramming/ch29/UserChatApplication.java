package org.wilson.concurrencyprogramming.ch29;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class UserChatApplication {
    public static void main(String[] args) {
        final var dispatcher = new AsyncEventDispatcher();
        dispatcher.registerChannel(UserOnlineEvent.class, new UserOnlineEventChannel());
        dispatcher.registerChannel(UserChatEvent.class, new UserChatEventChannel());
        dispatcher.registerChannel(UserOfflineEvent.class, new UserOfflineEventChannel());
        new UserChatThread(new User("Wilson"), dispatcher).start();
        new UserChatThread(new User("Bella"), dispatcher).start();
        new UserChatThread(new User("Vincent"), dispatcher).start();
    }
    record User(String name){
    }

    static class UserOnlineEvent extends Event {
        private final User user;

        public UserOnlineEvent(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }

    static class UserOfflineEvent extends UserOnlineEvent {
        public UserOfflineEvent(User user) {
            super(user);
        }
    }

    static class UserChatEvent extends UserOnlineEvent {
        private final String message;
        public UserChatEvent(User user, String message){
            super(user);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    static class UserOnlineEventChannel extends AsyncChannel {
        @Override
        protected void handle(Event message) {
            var event = (UserOnlineEvent) message;
            System.out.println("The User [" + event.getUser().name() + "] is online.");
        }
    }

    static class UserOfflineEventChannel extends AsyncChannel {
        @Override
        protected void handle(Event message) {
            var event = (UserOfflineEvent) message;
            System.out.println("The User [" + event.getUser().name() + "] is offline.");
        }
    }

    static class UserChatEventChannel extends AsyncChannel {
        @Override
        protected void handle(Event message) {
            var event = (UserChatEvent) message;
            System.out.println("The User [" + event.getUser().name() + "] say: " + event.getMessage());
        }
    }

    static class UserChatThread extends Thread {
        private final User user;
        private final AsyncEventDispatcher dispatcher;

        public UserChatThread(User user, AsyncEventDispatcher dispatcher) {
            super(user.name);
            this.user = user;
            this.dispatcher = dispatcher;
        }

        @Override
        public void run() {
            try {
                this.dispatcher.dispatch(new UserOnlineEvent(this.user));
                IntStream.range(0, 5).forEach(i -> UserChatThread.this.dispatcher.dispatch(
                        new UserChatEvent(UserChatThread.this.user, UserChatThread.this.getName() + "-Hello-" + i)));
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.dispatcher.dispatch(new UserOfflineEvent(this.user));
            }
        }
    }
}
