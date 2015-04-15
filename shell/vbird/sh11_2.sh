#!/bin/bash
#Program:
#   Let user input one, two, three and show in screen
#History:
#   2015/04/15  Wilson Xu   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

function printit() {
    echo -n "Your choice is "
}

echo "This program will print your selection!"
#read -p "Input your choice: " choice
#case $choice in
case $1 in
    "one")
        printit; echo $1 | tr 'a-z' 'A-Z'
        ;;
    "two")
        printit; echo $1 | tr 'a-z' 'A-Z'
        ;;
    "three")
        printit; echo $1 | tr 'a-z' 'A-Z'
            ;;
    *)
        echo "Usage {one|two|three}"
        ;;
esac
