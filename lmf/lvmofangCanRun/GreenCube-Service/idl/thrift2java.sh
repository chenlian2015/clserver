#!/bin/sh

for thrift in `find ./ -name "*.thrift"`;
do
        fileName=$(basename $thrift)
        filePath=$thrift
        
				thrift --gen java $filePath
				thrift --gen js:node $filePath
done