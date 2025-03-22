**Ai News Curator Backend**

A Spring Boot-based backend for a full-stack personalized news aggregator. The backend integrates with external news APIs, uses AI-powered filtering (via DJL with "all-MiniLM-L6-v2" and a BERT uncased tokenizer), and stores user preferences in a PostgreSQL database. This project is deployed on [Render](https://render.com).

**Table of Contents**

- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Setup and Installation](#setup-and-installation) 
  - [Local Development](#local-development)
  - [Environment Variables](#environment-variables)
- [Database Configuration](#database-configuration)
- [Building and Running](#building-and-running)
- [Deployment](#deployment)
- [Troubleshooting](#troubleshooting)
- [References and Resources](#references-and-resources)
- [License](#license)

**Overview**

This backend serves as the core API for a personalized news aggregator application. It fetches news from external APIs, summarizes articles, and applies AI-powered filtering based on user preferences. Users can update their preferences via dedicated endpoints, and the filtered news is served through a REST API.

**Features**

- **News Fetching:**
  Retrieves trending news articles using [NewsAPI](https://newsapi.org/).
- **AI-Powered Filtering & Summarization:**
  Uses [DJL](https://djl.ai/) Model Zoo to load pre-trained models (e.g., "all-MiniLM-L6-v2") and employs a BERT uncased tokenizer ("bert-base-uncased") to compute text embeddings and filter news based on cosine similarity.
- **User Preferences:**
  Provides REST endpoints to save and retrieve user preferences, which influence the personalized news feed.
- **Database Integration:**
  Uses PostgreSQL as the persistence layer (managed via Render).
- **CORS Enabled:**
  Configured to allow cross-origin requests from the frontend domain.

**Technologies**

- **Backend Framework:** Spring Boot 3.x
- **Build Tool:** Maven
- **Programming Language:** Java 21
- **Database:** PostgreSQL (managed on Render)
- **AI Integration:** DJL (Deep Java Library)
- **External API:** NewsAPI
- **Docker :** To create a docker image for deploying on Render

**Setup and Installation**

**Local Development**

1. **Clone the Repository:**

   bash

   Copy

   git clone https://github.com/YOUR\_USERNAME/newsAggregator-backend.git

   cd newsAggregator-backend

1. **Configure Environment Variables:**

   Modify application.properties file in src/main/resources with content similar to:

   properties

   Copy

   # Database connection settings (local fallback)

   # spring.datasource.url=jdbc:postgresql://localhost:5432/newsaggregator

   # spring.datasource.username=postgres

   # spring.datasource.password=yourpassword

   # Use environment variables on Render:

   spring.datasource.url=${DATABASE\_URL}

   spring.datasource.username=${DATABASE\_USER}

   spring.datasource.password=${DATABASE\_PASSWORD}

   # NewsAPI configuration

   newsapi.apiKey=fdb30567ea9649d7ab7dac3755e3a355

   newsapi.baseUrl=https://newsapi.org/v2/top-headlines

   # JPA & Hibernate configuration

   spring.jpa.hibernate.ddl-auto=update

   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

   # Show SQL in the console (for debugging)

   spring.jpa.show-sql=true

   logging.level.root=DEBUG

   logging.level.org.springframework=DEBUG

1. **Set JAVA\_HOME:**
   Ensure JAVA\_HOME is correctly set (e.g., run export JAVA\_HOME=$(/usr/libexec/java\_home) on macOS).
1. **Build the Project:**

   bash

   Copy

   ./mvnw clean package

**Environment Variables**

When deploying to Render, set the following environment variables in the Render dashboard:

- **DATABASE\_URL:**
  The JDBC URL for your PostgreSQL database. Example:

  bash

  Copy

  jdbc:postgresql://dpg-cvfaaqlsvqrc73cutt9g-a.ohio-postgres.render.com:5432/newsaggregator\_db?user=newsaggregator\_db\_user&password=ooxgFozD5LlONUyAlnZKg0U0tGVUShFp

- **DATABASE\_USER:**
  render-user
- **DATABASE\_PASSWORD:**
  render-generated

**Database Configuration**

The project uses PostgreSQL with Hibernate/JPA. To enforce data consistency, a unique constraint on userId is recommended in the UserPreference entity:

java

Copy

@Entity

@Table(name = "user\_preference", uniqueConstraints = @UniqueConstraint(columnNames = "userId"))

public class UserPreference {

`    `// ...

}

**Building and Running**

**Running Locally**

After building, you can run the jar:

bash

Copy

java -jar target/news\_aggregator-0.0.1-SNAPSHOT.jar

Access endpoints such as:

- **Fetch News:**
  GET http://localhost:8080/api/news?category=technology&userId=user123
- **User Preferences:**
  POST http://localhost:8080/api/preferences/update

**Running via Docker**

If you prefer containerization, add a Dockerfile to the root of the project:

dockerfile

Copy

\# Stage 1: Build with Maven

FROM maven:3.8.7-openjdk-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

\# Stage 2: Run the Jar

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/news\_aggregator-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

Build and run your Docker container locally:

bash

Copy

docker build -t news-aggregator-backend

docker run -p 8080:8080 news-aggregator-backend

**Deployment**

**Deploying on Render**

1. **Push Your Code to GitHub:**
   Ensure your repository is updated on GitHub.
1. **Create a New Web Service on Render:**
   1. Choose your repository.
   1. For language, select Docker if you’re using the Dockerfile.
   1. Configure build commands as needed.
1. **Set Environment Variables on Render:**
   Use the Render dashboard to set DATABASE\_URL, DATABASE\_USER, and DATABASE\_PASSWORD as described above.
1. **Deploy:**
   Render will build and deploy your service. You can monitor the logs for any errors.
1. **Access Your Live Service:**
   Your backend will be available at a URL like:
   https://newsaggregator-backend.onrender.com/api/news?category=technology&userId=user123

**Troubleshooting**

- **JDBC Connection Errors:**
  Ensure your DATABASE\_URL is in the correct JDBC format and that all environment variables are properly set.
- **CORS Issues:**
  Use global CORS configuration (see WebConfig.java) to allow requests from your frontend domain.
- **Build Failures:**
  Check the Render logs for detailed errors. Make sure your Maven wrapper (mvnw) and Dockerfile are in the project root.
- **Pom**

**References and Resources**

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [DJL (Deep Java Library)](https://djl.ai/)
- NewsAPI Documentation
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Render Deployment Docs](https://render.com/docs)
- Docker Documentation
- [Tailwind CSS (for frontend)](https://tailwindcss.com/)
- [Heroicons](https://heroicons.com/)

**License**


