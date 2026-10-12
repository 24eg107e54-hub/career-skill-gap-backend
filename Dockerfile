# Stage 1: Build stage with Maven and Java 21
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy application source code and package jar
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime image with lightweight Java 21 JRE
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy executable jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose server port (default 8080 or dynamic port via $PORT)
EXPOSE 8080

# Execute Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
