FROM  davidcaste/alpine-tomcat:jdk8tomcat7

MAINTAINER tsungtwu

## update timezone
ARG WAR_NAME
RUN echo "Asia/Taipei" > /etc/timezone

RUN rm -rf /opt/tomcat/webapps/ROOT & \
 rm -rf /opt/tomcat/webapps/examples && \
 rm -rf /opt/tomcat/webapps/docs


ADD  ./${WAR_NAME} /opt/tomcat/webapps/ROOT.war

#EXPOSE 8009

CMD ["/opt/tomcat/bin/catalina.sh" , "run"]