println "//" + "START:MULTIPLEASSIGN_OUTPUT"
def splitName(fullName) {
    fullName.split(' ')
}
def (firstName, lastName) = splitName 'James Bond'
println "$lastName, $firstName $lastName"
println "//" + "END:MULTIPLEASSIGN_OUTPUT"

println "//" + "START:SWAP_OUTPUT"
def name1 = 'Thomson'
def name2 = 'Thompson'
println "$name1 and $name2"
(name1, name2) = [name2, name1]
println "$name1 and $name2"
println "//" + "END:SWAP_OUTPUT"

println "//" + "START:EXCESS_OUTPUT"
def (cat, mouse) = ['Tom', 'Jerry', 'Spike', 'Tyke']
println "$cat and $mouse"
println "//" + "END:EXCESS_OUTPUT"

println "//" + "START:LESS_OUTPUT"
def (first, second, third) = ['Tom', 'Jerry']
println "$first, $second, and $third"
println "//" + "END:LESS_OUTPUT"

third = 'Tyke'
(first, second, third) = ['Tom', 'Jerry']
println "$first, $second, and $third"

try {
    def (one, int two) = [1]
} catch(ex) {
    println ex.getClass()
}
