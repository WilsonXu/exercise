#!/bin/bash
#Program:
#   Use loop to try find your input
#History:
#   2015/04/15  Wilson Xu   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

until [ "$yn" == "yes" ] || [ "$yn" == "YES" ]
do
    read -p "please input yes/YES to stop this program: " yn
done
