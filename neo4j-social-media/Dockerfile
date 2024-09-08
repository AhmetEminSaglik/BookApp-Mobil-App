FROM openjdk:17-jdk-slim
WORKDIR /app
#COPY target/neo4j-social-media-1.0.jar /app/neo4j-social-media.jar
COPY target/neo4j-social-media-1.0.jar /app/neo4j-social-media.jar
RUN mkdir -p /app/src/main/resources
COPY src/main/resources/indexes.txt /app/src/main/resources/indexes.txt
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "neo4j-social-media.jar"]
