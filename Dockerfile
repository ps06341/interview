From tomcat:8.5.82-jdk8-temurin-focal
RUN rm -rvf /usr/local/tomcat/webapps/*.war
COPY /target/*.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]