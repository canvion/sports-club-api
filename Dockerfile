FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

#copiar archivos de Maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

#dar permisos de ejecución
RUN chmod +x mvnw

#descargar dependencias
RUN ./mvnw dependency:resolve || true

#copiar código fuente
COPY src ./src

#construir la aplicación
RUN ./mvnw clean package -DskipTests

#imagen ligera
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

#copiar el JAR desde la etapa de build
COPY --from=builder /app/target/*.jar app.jar

# puerto
EXPOSE 8080

#inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
