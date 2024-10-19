## Use an official OpenJDK runtime as a parent image
#FROM openjdk:17-jdk-slim
#
#RUN mvn clean package
## Argument for the JAR file location (build output)
#ARG JAR_FILE=target/*.jar
#
## Copy the JAR file into the container
#COPY ${JAR_FILE} /app/authentication-service.jar
#
## Set the working directory inside the container
#WORKDIR /app
#
## Expose the application port
#EXPOSE 8083
#
## Run the Spring Boot application
#ENTRYPOINT ["java", "-jar", "authentication-service.jar"]


FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./pom.xml .

RUN mvn dependency:go-offline -B

COPY ./ ./

RUN mvn clean package -DskipTests

ARG JAR_FILE=target/authentication-service.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "authentication-service.jar"]
