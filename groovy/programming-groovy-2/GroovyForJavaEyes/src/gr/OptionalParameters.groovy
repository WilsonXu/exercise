println "//" + "START:OPTIONAL_OUTPUT"
def log(x, base = 10) {
    Math.log(x) / Math.log(base)
}

println log(1024)
println log(1024, 10)
println log(1024, 2)
println "//" + "END:OPTIONAL_OUTPUT"

println "//" + "START:ARRAY_OUTPUT"
def task(name, String[] details) {
    println "$name - $details"
}

task "Call", '123-456-7890'
task "Call", '123-456-789', '231, 546, 0987'
task "Check Mail"

println "//" + "END:ARRAY_OUTPUT"