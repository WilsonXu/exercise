#!/bin/bash
#Program:
#   Try to use loop to calculate the result of "1+2+3+4+...+100"
#History:
#   2015/04/15  Wilson Xu   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

s=0
i=0
while [ "$i" != 100 ]
do
    i=$(($i+1))
    s=$(($s+$i))
done
echo "The result of '1+2+3+4+...+100' is ==> $s"
