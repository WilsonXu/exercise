#!/bin/bash
#Program:
#   Try to calculate 1+2+3+...+100
#History:
#   2015/04/15  Wilson Xu   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

s=0
for (( i=1; i<=100; i++ ))
do
    s=$(($s+$i))
done
echo "The result of '1+2+3+...+100' is ==> $s"
