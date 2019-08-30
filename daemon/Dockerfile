FROM openjdk:8-jdk-alpine

LABEL maintainer="aime.mathieu1@gmail.com"

VOLUME /tmp

EXPOSE 8088

COPY target/happy-hour-finder-*.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]