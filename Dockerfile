# Etapa 1: Construcción del jar
FROM maven:3.9.6-eclipse-temurin-17 as builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/TiroConArcio-0.0.1-SNAPSHOT.jar app.jar

# Render usa la variable PORT
EXPOSE 8080
CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
