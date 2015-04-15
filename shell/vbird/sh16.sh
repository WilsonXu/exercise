#!/bin/bash
#Program:
#   let user input a directory and find the whole file's permission.
#History:
#   2015/04/15  Wilson Xu   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

#1. to query if the directory exists
read -p "Please input a directory: " dir
if [ "$dir" == "" ] || [ ! -d "$dir" ]; then
    echo " The $dir is NOT exit in your system."
    exit 1
fi

#2. start to test file
filelist=`ls $dir`
for filename in $filelist
do
    perm=""
    test -r "$dir/$filename" && perm="$perm readable"
    test -w "$dir/$filename" && perm="$perm writable"
    test -x "$dir/$filename" && perm="$perm executable"
    echo "The file $dir/$filename's permission is $perm"
done
