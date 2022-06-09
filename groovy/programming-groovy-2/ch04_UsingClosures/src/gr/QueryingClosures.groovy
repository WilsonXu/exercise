package gr

def completeOrder(amount, taxComputer) {
    tax = 0;
    if (taxComputer.maximumNumberOfParameters == 2) {
        tax = taxComputer amount, 6.05
    } else {
        tax = taxComputer amount
    }
    println "Sales tax is $tax"
}

completeOrder(100) { it * 0.082_5 }
completeOrder(100) { amount, rate -> amount * (rate / 100) }