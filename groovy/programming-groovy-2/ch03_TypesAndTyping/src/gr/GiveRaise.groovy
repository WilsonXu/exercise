package gr

import ja.Employee
import ja.Executive


void giveRaise(Employee employee) {
    employee.raise(new BigDecimal(10_000.00))
}

giveRaise new Employee()
giveRaise new Executive()