FROM tomcat:10.1-jdk21

COPY StudentManagementSystem.war /usr/local/tomcat/webapps/StudentManagementSystem.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
