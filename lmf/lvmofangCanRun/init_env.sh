# Before execute this script, you need to do these below.
# 1edit /etc/profile export PATH=$PATH:/usr/local/program/apache-maven-3.3.9/bin
# 2remove all installed open jdk like these
#  rpm -qa | grep java
#  rpm -e --nodeps java-1.6.0-openjdk-1.6.0.0-11.1.13.4.el6.x86_64
#  rpm -e --nodeps java-1.7.0-openjdk-1.7.0.65-2.5.1.2.el6_5.x86_64
#

mkdir /software

# install svn
yum -y install subversion


mkdir /usr/local/program

# install maven
cd /usr/local/program
wget http://192.168.1.115/files/apache-maven-3.3.9-bin.tar.gz
tar zxvf apache-maven-3.3.9-bin.tar.gz
rm -f apache-maven-3.3.9-bin.tar.gz

# install jdk 
rpm -e --nodeps java-1.6.0-openjdk-1.6.0.0-11.1.13.4.el6.x86_64
rpm -e --nodeps java-1.7.0-openjdk-1.7.0.65-2.5.1.2.el6_5.x86_64
wget http://192.168.1.115/files/jdk-7u79-linux-x64.rpm
chmod +x jdk-7u79-linux-x64.rpm
rpm -ivh jdk-7u79-linux-x64.rpm
rm -f jdk-7u79-linux-x64.rpm

# downloads codes
cd /software
mkdir SVN
cd SVN
svn co https://192.168.1.31:8433/svn/lvmofang lvmofang

cd /software
wget http://192.168.1.115/files/tomcat_production.tar.gz
tar zxvf tomcat_production.tar.gz
rm -f tomcat_production.tar.gz



