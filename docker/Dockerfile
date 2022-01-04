FROM openjdk:11-jre-slim

WORKDIR /usr/app
COPY ./app.jar /usr/app/

ENTRYPOINT ["java", "-jar", "app.jar", "--port=8080"]
