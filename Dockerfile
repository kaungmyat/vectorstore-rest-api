FROM maven:3.9.8-eclipse-temurin-21-alpine AS builder
WORKDIR /src
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /src
COPY --from=builder /src/target/*.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]