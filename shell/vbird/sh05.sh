#!/bin/bash
#Program:
#   Let user input a file name, the program will search the filename
#   1.) exist? 2.) file/directory? 3.) file permissions
#History:
#   2015/04/06  Wilson Xu   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

#1. let user enter the name of file and alert if it is a empty string
echo -e "The program will show you that filename is exist which input by you.\n\n"
read -p "Input a file name: " filename
test -z $filename && echo "You MUST input a filename." && exit 0

#2. if file exists
test ! -e $filename && echo "The file name $filename DO NOT exits" && exit 0

#3. display file attribute
test -f $filename && filetype="regulare file"
test -d $filename && filetype="directory"
test -r $filename && perm="readable"
test -w $filename && perm="$perm writable"
test -x $filename && perm="$perm executable"

#4. start to display information
echo "The filename: $filename is a $filetype"
echo "And the permission are: $perm"
