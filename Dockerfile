# Stage 1: Build with Maven
FROM maven:3.8.7-jdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Run the application
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/your-app.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]