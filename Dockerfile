FROM gauravjava11maven3.8.4
WORKDIR /app
COPY . .
COPY pom.xml ./
COPY src ./src
COPY testng.xml ./
COPY reports /app/reports
COPY logs ./logs
ENTRYPOINT mvn clean install