#!/bin/bash
#script to filter out bad email addres
awk --re-interval '/^([a-zA-Z0-9_\-\.\+])+@([a-zA-Z_\-\.]+)\.([a-zA-Z]{2,5})$/{print $0}'
