package org.wilson.corejava.ch12.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Bank {
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
        if (log.isInfoEnabled()) {
            log.info(String.format("%10.2f from %d to %d Total Balance: %10.2f%n",
                    amount, from, to, this.getTotalBalance()));
        }
    }
}
