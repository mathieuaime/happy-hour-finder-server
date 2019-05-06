FROM openjdk:8-jdk-alpine

LABEL maintainer="aime.mathieu1@gmail.com"

VOLUME /tmp

EXPOSE 8088

COPY target/happy-hour-finder-backend-*.jar app.jar

ENV JAVA_OPTS=""

ENTRYPOINT ["java","$JAVA_OPTS","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]