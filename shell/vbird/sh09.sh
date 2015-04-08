#!/bin/bash
#Program:
#   Using nestat and grep to detect www, ssh, ftp and mail services
#History:
#   2015/04/08  Wilson Xu   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

#1. prompt
echo "Now, the services of your mac system will be detected!"
echo -e "The www, ssh, ftp and mail will be dected!\n"

#2. start to dectect and display some message
testing=`netstat -tuln | grep ":80"`
if [ "$testing" != "" ]; then
    echo "www is running in your system"
fi
testing=`netstat -tuln | grep ":21"`
if [ "$testing" != "" ]; then
    echo "ftp is running in your system"
fi
testing=`netstat -tuln | grep ":22"`
if [ "$testing" != "" ]; then
    echo "ssh is running in your system"
fi
testing=`netstat -tuln | grep ":25"`
if [ "$testing" != "" ]; then
    echo "mail is running in your system"
fi
