#!/bin/bash

workdir="."
CONTAINER_NAME=spring-geoip2-api
WAR_NAME=spring4mvc-geoip2-rest-api-1.0.0.war
DATE=$(date +"%Y%m%d")
PORT=8080
echo $DATE

mvn clean package

cp $workdir/target/$WAR_NAME $workdir


docker build -t $CONTAINER_NAME:$DATE --build-arg WAR_NAME=$WAR_NAME .
docker stop $CONTAINER_NAME
docker rm $CONTAINER_NAME
docker run -d -p $PORT:8080 --name $CONTAINER_NAME $CONTAINER_NAME:$DATE
