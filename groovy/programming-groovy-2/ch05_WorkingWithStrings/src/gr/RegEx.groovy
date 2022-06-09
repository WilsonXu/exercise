package gr

println "//" + "START:CREATE_PATTERNOUTPUT"
obj = ~'hello'
println obj.getClass().name
println "//" + "END:CREATE_PATTERNOUTPUT"

println "//" + "START:MATCHOUTPUT"
pattern = ~'(G|g)roovy'
text = 'Groovy is hip'
if (text =~ pattern)
    println "match"
else
    println "no match"

if (text ==~ pattern)
    println "match"
else
    println "no match"
println "//" + "END:MATCHOUTPUT"

println "//" + "START:MATCHES_OUTPUT"
matcher = 'Groovy is groovy' =~ /(G|g)roovy/
print "Size of matcher is ${matcher.size()} "
println "with elements ${matcher[0]} and ${matcher[1]}}"
println "//" + "END:MATCHES_OUTPUT"

println "//" + "START:REPLACE_OUTPUT"
str = 'Groovy is groovy, really groovy'
println str
result = (str =~ /groovy/).replaceAll('hip')
println result
println "//" + "END:REPLACE_OUTPUT"