#!/bin/bash
#Program:
#   This program will show the user's choice
#History:
#   2015/04/06  Wilson Xu   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

read -p "Please input [Y/N]: " yn
[ "$yn" == "y" -o "$yn" == "Y" ] && echo "OK, continue" && exit 0
[ "$yn" == "n" -o "$yn" == "N" ] && echo "Oh, interrupt!" && exit 0
echo "I don't know what is your choise" && exit 0
