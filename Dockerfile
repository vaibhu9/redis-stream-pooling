FROM openjdk:23-jdk-slim

WORKDIR /app

COPY build/libs/redisstreams-0.0.1-SNAPSHOT.jar redisstreams.jar

CMD ["java", "-jar", "redisstreams.jar"]