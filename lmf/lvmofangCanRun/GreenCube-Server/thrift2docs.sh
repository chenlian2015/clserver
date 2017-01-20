#!/bin/sh
#
####################################
#                                  #
#  本工具用于自动生成Thrift接口文档参考 #
#                                  #
####################################

        
javadoc -d ./docs -author -version -encoding UTF-8 -charset UTF-8 -sourcepath ./src/main/idl/gen-java -subpackages com.cnd.greencube.server.service
