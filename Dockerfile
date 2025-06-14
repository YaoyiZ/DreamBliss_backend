FROM eclipse-temurin:20-jdk
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw package -DskipTests
CMD ["java", "-jar", "target/dreambliss_backend-0.0.1-SNAPSHOT.jar"]

