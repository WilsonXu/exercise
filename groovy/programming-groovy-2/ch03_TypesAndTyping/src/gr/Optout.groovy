package gr

import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode

@TypeChecked
class Sample {
    def method1() {
    }

    @TypeChecked(TypeCheckingMode.SKIP)
    def method2(str) {
        str.shout()
    }
}

println 'works'