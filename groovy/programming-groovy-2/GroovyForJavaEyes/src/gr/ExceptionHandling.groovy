def openFile(fileName) {
    new FileInputStream(fileName)
}

try {
    openFile("nonexixtentfile")
} catch (FileNotFoundException ex) {
    println "Oops: " + ex
}

try {
    openFile("nonexixtentfile")
} catch (ex) {
    println "Oops: " + ex
}