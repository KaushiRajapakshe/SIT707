FROM eclipse-temurin:17-jdk
RUN ls -la target
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
