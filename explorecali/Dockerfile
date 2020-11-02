FROM openjdk:13
WORKDIR /
ADD target/explorecali-3.0.0-SNAPSHOT.jar //
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=docker", "/explorecali-3.0.0-SNAPSHOT.jar"]
