#!/bin/sh


###############################
#                             #
#      App Server             #
#    开发期启动部署脚本          #
#                             #
###############################

APP_ROOT=/software/SVN/lvmofang/trunk

# 终止正在运行的进程
ID=`ps -ef | grep "tomcat_appserver" | grep -v "$0" | grep -v "grep" | awk '{print $2}'`
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

mvn clean
mvn compile
mvn package

#mvn javadoc:jar -f $APP_ROOT/GreenCube-Service/pom.xml

#rm -rf $APP_ROOT/GreenCube-AppServer/target/GreenCube-AppServer/docs
#mkdir $APP_ROOT/GreenCube-AppServer/target/GreenCube-AppServer/docs

# create javadoc
#\cp -rf $APP_ROOT/GreenCube-Service/target/GreenCube-Service-1.0-javadoc.jar $APP_ROOT/GreenCube-AppServer/target/GreenCube-AppServer/docs


#cd $APP_ROOT/GreenCube-AppServer/target/GreenCube-AppServer/docs
#unzip GreenCube-Service-1.0-javadoc.jar
#rm -f GreenCube-Service-1.0-javadoc.jar

# delete logs
rm -f /software/tomcat_appserver/logs/*

/software/tomcat_appserver/bin/startup.sh
