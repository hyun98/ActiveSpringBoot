FROM openjdk:11

ARG JAR_FILE=build/libs/jpashop-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /HOME/spring/app.jar

WORKDIR /HOME/spring/

EXPOSE 8080