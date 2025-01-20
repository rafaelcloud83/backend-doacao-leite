FROM eclipse-temurin:17-jre-alpine
COPY target/doacao-leite-0.0.1.jar /app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
