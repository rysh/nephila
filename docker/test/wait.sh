#!/bin/bash
res=0
while [ ! $res = 200 ]
do
  res=`curl -LI http://server:9000/ -o /dev/null -w '%{http_code}\n' -s`
  sleep 1
done
echo end
