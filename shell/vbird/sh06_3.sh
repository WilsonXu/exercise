#!/bin/bash
#Program:
#   This program will show the user's choice
#History:
#   2015/04/06  Wilson Xu   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

read -p "Please input [Y/N]: " yn
if [ "$yn" == "y" ] || [ "$yn" == "Y" ]; then
    echo "OK, continue"
elif [ "$yn" == "n" ] || [ "$yn" == "N" ]; then
    echo "Oh, interrupt!"
else
    echo "I don't know what is your choise"
fi
