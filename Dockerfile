# Docker Build Maven Stage
FROM maven:3.8.4-openjdk-17 AS build

# Copy only the POM file to cache dependencies
COPY ./pom.xml /opt/app/
WORKDIR /opt/app

# Download dependencies only if the POM file has changed
RUN mvn dependency:go-offline

# Copy the rest of the application and build
COPY ./src /opt/app/src/
RUN mvn clean package -DskipTests

# Run Spring Boot in Docker
FROM openjdk:17-jdk-slim

# Copy the JAR file from the build stage
COPY --from=build /opt/app/target/*.jar app.jar

# Set environment variable for the port
ENV PORT 8081

# Expose the port
EXPOSE $PORT

# Entry point for the application
ENTRYPOINT ["java", "-jar", "-Xmx1024M", "-Dserver.port=${PORT}", "app.jar"]
