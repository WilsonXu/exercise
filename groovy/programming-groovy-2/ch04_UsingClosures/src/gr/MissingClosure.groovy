package gr

def doSomething(closure) {
    if (closure) {
        closure()
    } else {
        println "Using default implementation"
    }
}

doSomething { println "Use specialized implementation" }
doSomething()