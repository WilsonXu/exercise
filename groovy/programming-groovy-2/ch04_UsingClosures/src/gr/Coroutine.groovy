package gr

def iterate(n, closuer) {
    1.upto(n) {
        println "In iterate with value $it"
        closuer it
    }
}

println "Calling iterate"
total = 0
iterate(4) {
    total += it
    println "In closure total so far is $total"
}
println "Done"