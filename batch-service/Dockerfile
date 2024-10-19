FROM openjdk:17

ARG JAR_FILE=target/*.jar

ADD ${JAR_FILE} batch-service.jar

ENTRYPOINT ["java", "-jar", "batch-service.jar"]

EXPOSE 8084