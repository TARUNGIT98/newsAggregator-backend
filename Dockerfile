# Stage 1: Build with Maven
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the Jar
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/news_aggregator-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
