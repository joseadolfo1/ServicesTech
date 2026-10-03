# Dockerfile del backend (Spring Boot + Gradle). Va en la carpeta del backend.
# Etapa 1: compilar
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .

# 1. Limpiar los finales de línea de Windows (CRLF a LF)
RUN sed -i 's/\r$//' gradlew

RUN chmod +x gradlew && ./gradlew bootJar -x test --no-daemon

# Etapa 2: ejecutar (imagen liviana, solo con Java)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-jar", "app.jar"]
