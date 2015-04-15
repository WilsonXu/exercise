#!/bin/bash
#Program:
#   Using for ... loop to print 3 animal
#History:
#   2015/04/15  Wilson Xu   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

for animal in dog cat elephant
do
    echo "There are ""$animal""s... "
done
