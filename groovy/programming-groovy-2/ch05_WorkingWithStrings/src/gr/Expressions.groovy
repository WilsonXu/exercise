package gr

println "//" + "START:EXPRESSION_OUTPUT"
value = 12
println "He paid \$${value} for that."
println "//" + "END:EXPRESSION_OUTPUT"


println "//" + "START:EVALUATE_OUTPUT"
what = new StringBuilder('fence')
text = "The cow jumped over the $what"
println text

what.replace(0, 5, 'moon')
println text
println "//" + "END:EVALUATE_OUTPUT"

println "//" + "START:CLASS_OUTPUT"
def printClassInfo(obj) {
    println "class: ${obj.getClass().name}"
    println "superclass: ${obj.getClass().superclass.name}"
}

val = 125
printClassInfo "The Stock closed at ${val}"
printClassInfo (/The Stock closed at ${val}/)
printClassInfo "This is a simple String"
println "//" + "END:CLASS_OUTPUT"