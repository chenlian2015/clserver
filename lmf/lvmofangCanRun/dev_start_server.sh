#!/bin/sh


###############################
#                             #
#      Server             #
#    开发期启动部署脚本          #
#                             #
###############################

APP_ROOT=/software/SVN/lvmofang/trunk

# 终止正在运行的进程
ID=`ps -ef | grep "tomcat_server" | grep -v "$0" | grep -v "grep" | awk '{print $2}'`
echo $ID
echo "---------------"
for id in $ID
do
kill -9 $id
echo "killed $id"
done
echo "---------------"

cd $APP_ROOT

svn update
mvn clean package


# delete logs
rm -f /software/tomcat_server/logs/*

/software/tomcat_server/bin/startup.sh