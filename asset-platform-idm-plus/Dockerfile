# FROM java
FROM java:8-jre
#FROM hub.c.163.com/library/java:8-jre
MAINTAINER hjhu
#RUN mkdir -p /platform/idm
#WORKDIR /platform/idm
EXPOSE 8083
#VOLUME /docker
ADD target/*.jar app.jar
ENTRYPOINT ["java","-Dfile.encoding=utf-8","-jar","app.jar"]
CMD ["--spring.profiles.active=dev"]
# ENV MYSQL_USER root
# ENV MYSQL_PASS root
# ENV MYSQL_URL jdbc:mysql://localhost:3306/asset-platform-idm?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8
