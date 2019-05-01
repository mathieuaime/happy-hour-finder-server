FROM openjdk:8-jdk-alpine

LABEL maintainer="aime.mathieu1@gmail.com"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]