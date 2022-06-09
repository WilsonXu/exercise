package gr

class Resource{
    def open() {
        print "opened..."
    }

    def close() {
        print "closed"
    }

    def read() {
        print "read..."
    }
    def write() {
        print "write..."
    }

    def static use(closure) {
        def r = new Resource()
        try {
            r.open()
            closure(r)
        } finally {
            r.close()
        }
    }
}

println "//" + "START:NOCLOSE_OUTPUT"
def resource = new Resource()
resource.open()
resource.read()
resource.write()
println "\n//" + "END:NOCLOSE_OUTPUT"

println "//" + "START:CLOSE_OUTPUT"
Resource.use{ res ->
    res.read()
    res.write()
}
println "\n//" + "END:CLOSE_OUTPUT"
