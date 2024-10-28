FROM openjdk:17-jdk-slim AS build

RUN apt-get update && apt-get install -y maven
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY ./ ./
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 4953

ENTRYPOINT ["java", "-jar", "app.jar"]
