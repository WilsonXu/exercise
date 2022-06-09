package gr

println "//" + "START:LITERALOUTPUT"
println  'He said, "That is Groovy"'
println "//" + "END:LITERALOUTPUT"

println "//" + "START:CLASSOUTPUT"
str = 'A string'
println str.getClass().name
println "//" + "END:CLASSOUTPUT"

println "//" + "START:EXPRESSION_OUTPUT"
value = 25
println 'The value is ${value}'
println "//" + "END:EXPRESSION_OUTPUT"

println "//" + "START:INDEX_OUTPUT"
str = 'hello'
println str[2]
try {
    str[2] = '!'
} catch(Exception ex) {
    println ex
}
println "//" + "END:INDEX_OUTPUT"