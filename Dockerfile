FROM maven:4.0.0-openjdk-22 AS build

WORKDIR /app

COPY ./pom.xml /app

COPY ./src /app/src

RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:22-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD \["java", "-jar", "app.jar"\]