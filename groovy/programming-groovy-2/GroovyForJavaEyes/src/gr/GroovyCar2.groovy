class Car {
    private miles = 0
    final year

    Car(theYear) {
        year = theYear
    }

    def getMiles() {
        println "getMiles called"
        miles
    }

    private void setMiles(miles) {
        throw new IllegalAccessException("you're not allowed to change miles")
    }

    def drive(dist) {
        if (dist > 0) {
            miles += dist
        }
    }
}

car = new Car(2008)
println "Year: $car.year"
println "Miles: $car.miles"
println 'Driving'
car.drive 10
println "Miles: $car.miles"

try {
    print 'Can I set the year? '
    car.year = 1_900
} catch(groovy.lang.ReadOnlyPropertyException ex) {
    println ex.message
}

try {
    print 'Can I set the miles? '
    car.miles = 12
} catch(IllegalAccessException ex) {
    println ex.message
}
