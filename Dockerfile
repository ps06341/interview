From tomcat:8.5.82-jdk8-temurin-focal
RUN rm -rvf /usr/local/tomcat/webapps/*.war
COPY /target/usermanagement.war /usr/local/tomcat/webapps/usermanagement.war
CMD ["catalina.sh", "run"]
EXPOSE 8080