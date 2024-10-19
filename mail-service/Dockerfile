FROM openjdk:17

ARG JAR_FILE=target/*.jar

ADD ${JAR_FILE} mail-service.jar

ENTRYPOINT ["java", "-jar", "inventory-service.jar"]

EXPOSE 8088