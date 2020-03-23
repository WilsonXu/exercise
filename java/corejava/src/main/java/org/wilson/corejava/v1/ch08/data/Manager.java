package org.wilson.corejava.v1.ch08.data;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wilson on 2020/3/24.
 */
public class Manager extends Employee {
    @Getter
    @Setter
    private double bonus;

    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        this.bonus = 0;
    }

    @Override
    public double getSalary() {
        double baseSalary = super.getSalary();
        return baseSalary + this.bonus;
    }
}
