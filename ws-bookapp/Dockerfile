FROM openjdk:22-jdk-slim
WORKDIR /app
COPY target/bookapp-1.0.jar /app/bookapp.jar
COPY src/main/resources/indexes.txt /app/src/main/resources/indexes.txt
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bookapp.jar"]