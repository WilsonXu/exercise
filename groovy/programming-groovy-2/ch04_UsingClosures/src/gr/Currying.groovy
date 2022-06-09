package gr

import java.text.DateFormat

def tellFortunes(closure) {
    Date date = DateFormat.getDateInstance(DateFormat.SHORT).parse("09/20/2012")
    postFortune = closure.curry date
    postFortune "Your day is filled with ceremony"
    postFortune "They're features, not bugs"
}

tellFortunes { date, fortune -> println "Fortune for  $date is '$fortune'" }