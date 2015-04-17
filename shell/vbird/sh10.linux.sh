#!/bin/bash
#Program:
#   Trying to calculate your demobilization date at how many days later...
#History:
#   2015/04/08  Wilson Xu   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

#1. prompt the function of the program and how to input date format
echo "This program will try to calculate: "
echo "How may days about your demobilization date..."
read -p "Please input your demobilization date(YYYYMMDD ex>20150408): " date2

#3. validate the inpute by regular expression
date_d=`echo $date2 | grep '[0-9]\{8\}'`
if [ "$date_d" == "" ]; then
    echo "You input the wrong format of date..."
    exit o
fi

#3. star to calculate
declare -i date_dem=`date --date="$date2" +%s`
declare -i date_now=`date +%s`
declare -i date_total_s=$((date_dem-date_now))
declare -i date_d=$((date_total_s/60/60/24))
if [ "$date_total_s" -lt "0" ]; then
    echo "You had been demobilization before: " $((-1*date_d)) "ago"
else
    declare -i date_h=$(($(($date_total_s-$date_d*60*60*24))/60/60))
    echo "You will be demobilization after $date_d days and $date_h hours."
fi
