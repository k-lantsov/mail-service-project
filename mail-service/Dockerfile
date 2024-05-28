FROM eclipse-temurin:17-jre-alpine
WORKDIR /opt/app
COPY target/mail-service-0.0.1-SNAPSHOT.jar mail-ms.jar
ENTRYPOINT ["java", "-jar", "mail-ms.jar"]