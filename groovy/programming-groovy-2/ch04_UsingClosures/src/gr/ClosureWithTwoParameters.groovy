package gr

import java.text.DateFormat

println "//" + "START: OUTPUT"
def tellFortune(closure) {
    closure DateFormat.getDateInstance(DateFormat.SHORT).parse("09/20/2012"), "Your day is filled with ceremony"
}
tellFortune {date, fortune -> println "Fortune for $date is '$fortune'"}
println "//" + "END: OUTPUT"

tellFortune() {Date date, fortune -> println "Fortune for ${date} is '${fortune}'"}
