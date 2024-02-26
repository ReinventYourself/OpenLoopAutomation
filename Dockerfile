FROM maven:3.8.4-openjdk-11
WORKDIR /app
COPY pom.xml ./
COPY src ./src
COPY testng.xml ./
COPY reports /app/reports
COPY logs ./logs
ENTRYPOINT  mvn clean install