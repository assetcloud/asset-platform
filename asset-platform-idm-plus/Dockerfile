FROM java
MAINTAINER hjhu
RUN mkdir -p /platform/idm
WORKDIR /platform/idm
EXPOSE 8084
VOLUME /docker
ADD ./target/asset-platform-idm-plus-1.1.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#CMD ["--spring.profiles.active=test"]
ENV MYSQL_USER root
ENV MYSQL_PASS root
ENV MYSQL_URL jdbc:mysql://10.1.18.178:3306/asset-platform-idm?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8
