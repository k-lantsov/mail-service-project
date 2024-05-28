FROM eclipse-temurin:17-jre-alpine
WORKDIR /opt/app
COPY target/answering-service-0.0.1-SNAPSHOT.jar answering-ms.jar
ENTRYPOINT ["java", "-jar", "answering-ms.jar"]