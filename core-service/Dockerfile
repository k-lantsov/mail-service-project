FROM eclipse-temurin:17-jre-alpine
WORKDIR /opt/app
COPY target/core-service-0.0.1-SNAPSHOT.jar core-ms.jar
ENTRYPOINT ["java", "-jar", "core-ms.jar"]