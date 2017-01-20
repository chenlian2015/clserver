#!/bin/sh
####################################
#                                  #
#                   #
#                             #
####################################

# maven
svn update
mvn clean package install

# javadoc
#\cp -rf GreenCube-Service/ GreenCube-AppServer/target/GreenCube-AppServer/docs


# delete logs
rm -f /software/tomcat_appserver/logs/*

# tomcat
/software/tomcat_appserver/bin/startup.sh
