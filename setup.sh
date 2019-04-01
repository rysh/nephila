#!/bin/bash

configDir=../nephila-config

if [ -e $configDir ]; then
	echo $configDir exists.
else
	echo creating... $configDir
    mkdir $configDir
    echo
    tree $configDir
    echo config created
fi