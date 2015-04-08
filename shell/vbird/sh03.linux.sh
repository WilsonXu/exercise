#!/bin/bash
#Program:
#   User can keyin filename to touch 3 new files
#History:
#   2015/04/06  Wilson Xu   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

#1. let user enter the file name and get the variant fileuser
echo -e "I will use 'touch' command to create 3 files."
read -p "Please input the filename what you want: " fileuser

#2. to avoid enter <Enter> opotionally, use variant to analyze if set file name successfully.
filename=${fileuser:-"filename"}

#3. use date command to get the filename to need
date1=`date --date='-2 day' +%Y%m%d`
date2=`date --date='-1 day' +%Y%m%d`
date3=`date +%Y%m%d`
file1="$filename""$date1"
file2="$filename""$date2"
file3="$filename""$date3"

#4. create files
touch $file1
touch $file2
touch $file3
