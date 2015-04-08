#!/bin/bash
#Program:
#   User can input 2 integer to cross by!
#History:
#   2015/04/06  Wilson Xu   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

echo -e "You SHOULD input 2 numbers, I will cross they!\n"
read -p "first number: " firstnu
read -p "second number: " secnu
total=$((firstnu*secnu))
echo -e "\nThe number $firstnu * $secnu is ==> $total"
