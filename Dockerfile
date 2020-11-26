## Builder Image
FROM gradle:6.7-jdk11 AS builder
ENV APP_HOME=/usr/app
COPY src $APP_HOME/src
COPY build.gradle settings.gradle gradle.properties $APP_HOME
WORKDIR $APP_HOME
RUN gradle clean build

## Runner Image
FROM adoptopenjdk/openjdk11:alpine
COPY --from=builder /usr/app/build/libs/crud-0.1-all.jar /usr/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]