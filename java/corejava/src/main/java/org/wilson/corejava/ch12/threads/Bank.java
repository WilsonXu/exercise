package org.wilson.corejava.ch12.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Bank {
    public static final Logger LOGGER = LoggerFactory.getLogger("org.wilson.corejava.ch12.threads.Bank");

    private final double[] accounts;

    public Bank(int n, double initialBalance) {
        this.accounts = new double[n];
        Arrays.fill(this.accounts, initialBalance);
    }

    public int size() {
        return this.accounts.length;
    }

    public double getTotalBalance() {
        return Arrays.stream(this.accounts).sum();
    }

    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) return;
        accounts[from] -= amount;
        accounts[to] += amount;
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("%10.2f from %d to %d Total Balance: %10.2f%n",
                    amount, from, to, this.getTotalBalance()));
        }
    }
}
