FROM tomcat:10.1

COPY StudentManagementSystem.war /usr/local/tomcat/webapps/

EXPOSE 8080	