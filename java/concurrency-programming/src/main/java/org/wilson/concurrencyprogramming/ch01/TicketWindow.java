package org.wilson.concurrencyprogramming.ch01;

public class TicketWindow extends Thread{
    private static final int MAX = 50;
    private static int index = 1;

    private final String name;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println("The current number of the ticket window " + this.name + " is: " + (index ++));
        }
    }

    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("No. 1");
        ticketWindow1.start();

        TicketWindow ticketWindow2 = new TicketWindow("No. 2");
        ticketWindow2.start();

        TicketWindow ticketWindow3 = new TicketWindow("No. 3");
        ticketWindow3.start();

        TicketWindow ticketWindow4 = new TicketWindow("No. 4");
        ticketWindow4.start();
    }
}
