FROM openjdk:11-jre-slim

WORKDIR /usr/app
COPY ./app.jar /usr/app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]