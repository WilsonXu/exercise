package org.wilson.corejava.v1.ch08.data;

import lombok.Getter;

import java.time.LocalDate;

/**
 * Created by wilson on 2020/3/24.
 */
public class Employee {
    @Getter
    private String name;
    @Getter
    private double salary;
    @Getter
    private LocalDate hireDate;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDate = LocalDate.of(year, month, day);
    }

    public void raiseSalary(double byPercent) {
        double raise = this.salary * byPercent / 100;
        this.salary += raise;
    }
}
