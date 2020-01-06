FROM java:8
WORKDIR /
ADD target/explorecali-2.0.0-SNAPSHOT.jar //
EXPOSE 8080
ENTRYPOINT [ "java", "-Dspring.profiles.active=mysql", "-jar", "/explorecali-2.0.0-SNAPSHOT.jar"]
