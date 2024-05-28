FROM maven:3.9.6-amazoncorretto-17-debian as builder
COPY pom.xml /tmp/
COPY src /tmp/src
WORKDIR /tmp
RUN mvn clean package -Dmaven.test.skip

FROM sapmachine:17.0.11-jre-ubuntu-20.04
COPY --from=builder /tmp/target/*.jar /tmp/mail-ms.jar
WORKDIR /tmp
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "mail-ms.jar"]