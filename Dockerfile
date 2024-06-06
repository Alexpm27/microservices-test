# Docker Build Maven Stage
FROM maven:3.8.6-openjdk-17 AS build

# Copy folder in docker
WORKDIR /opt/app
COPY ./ /opt/app

# Build the application
RUN mvn clean install -DskipTests

# Run Spring Boot in Docker
FROM openjdk:17-jdk-slim

# Copy the JAR file from the build stage
COPY --from=build /opt/app/target/*.jar test-service.jar

# Set environment variable for the port
ENV PORT 8081

# Expose the port
EXPOSE $PORT

# Entry point for the application
ENTRYPOINT ["java", "-jar", "-Xmx1024M", "-Dserver.port=${PORT}", "test-service.jar"]
