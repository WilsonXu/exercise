package gr

writer = new FileWriter('output.txt')
writer.write('!')

new FileWriter("output.txt").withWriter {writer -> writer.write 'a'}