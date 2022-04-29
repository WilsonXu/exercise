println '//START:GIT_OUTPUT'
println 'git help'.execute().text
println '//END:GIT_OUTPUT'

println '//START:PROC_OUTPUT'
println 'git help'.execute().getClass().name
println '//END:PROC_OUTPUT'

println '//START:LS_OUTPUT'
println 'ls -l'.execute().text
println '//END:LS_OUTPUT'

println '//START:GROOVY_OUTPUT'
println '/Users/wilson/.sdkman/candidates/groovy/current/bin/groovy -v'.execute().text
println '//END:GROOVY_OUTPUT'
