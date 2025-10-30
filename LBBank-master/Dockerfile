FROM maven:3.9.11-eclipse-temurin-17 AS build
WORKDIR /LLBank

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY --from=build /LLBank/target/LBBank.jar app.jar
COPY resources /app/resources

ENTRYPOINT ["java", "-jar", "app.jar"]