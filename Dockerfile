# Use Maven builder image to compile
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app

# Copy source files
COPY pom.xml .
COPY src ./src

# Build the project and package jar
RUN mvn clean package -DskipTests

# Use a minimal JRE image for running
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the jar from builder stage
COPY --from=build /app/target/LibraryManagementSystem1-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
