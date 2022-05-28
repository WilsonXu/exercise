package gr

@groovy.transform.TypeChecked
def printInReverse(String str) {
  println str.reverse() //No problem
}
printInReverse 'hello'