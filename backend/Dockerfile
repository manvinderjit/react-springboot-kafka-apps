# Stage 1: Build stage with Maven and JDK
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Set working directory inside container
WORKDIR /app

# Copy pom.xml and download dependencies first (cache layer)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the jar file (skip tests for faster build, remove -DskipTests to run tests)
RUN mvn clean package -DskipTests

# Stage 2: Run stage with JRE only
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy jar from the build stage
COPY --from=build /app/target/*.jar ./backend.jar

# Expose port (make sure it matches your app)
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "backend.jar"]




# Use an official OpenJDK runtime as a parent image
# FROM eclipse-temurin:17-jdk-alpine

# # Set working directory inside the container
# WORKDIR /app

# # Copy the jar built by Maven into the container
# COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# # Expose the port your Spring Boot app runs on (usually 8080)
# EXPOSE 8080

# # Run the jar file
# ENTRYPOINT ["java", "-jar", "app.jar"]
