FROM maven:3.9.6-amazoncorretto-17-debian as builder
COPY pom.xml /tmp/
COPY src /tmp/src
WORKDIR /tmp
RUN mvn clean package -Dmaven.test.skip

FROM sapmachine:17.0.11-jre-ubuntu-20.04
COPY --from=builder /tmp/target/*.jar /tmp/core-ms.jar
WORKDIR /tmp
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "core-ms.jar"]