FROM openjdk:17-jdk-slim AS build

RUN apt-get update && apt-get install -y maven
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the project and build the JAR
COPY ./ ./
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy the JAR file from the 'build' stage
COPY --from=build /app/target/*.jar app.jar

# Expose the required port
EXPOSE 8083

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
