# Start with a lightweight JDK base image
FROM eclipse-temurin:17-jdk-alpine as builder

# Set working directory
WORKDIR /app

# Copy Maven wrapper & project files
COPY . .

# Build the application (requires mvnw and pom.xml)
RUN ./mvnw clean package -DskipTests

# ---- Final stage ----
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /app/target/frontend-0.0.1-SNAPSHOT.jar app.jar

# Expose the app port (optional, for documentation)
EXPOSE 8081

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]


# FROM eclipse-temurin:17-jdk-alpine

# WORKDIR /app

# COPY target/frontend-0.0.1-SNAPSHOT.jar app.jar

# EXPOSE 8080

# ENTRYPOINT ["java", "-jar", "app.jar"]
