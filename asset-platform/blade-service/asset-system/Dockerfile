FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER hjhu@hdu.edu.cn

RUN mkdir -p /asset/system

WORKDIR /asset/system

EXPOSE 9101

ADD ./target/asset-system.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--spring.profiles.active=dev"]
