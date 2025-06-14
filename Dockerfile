FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw package -DskipTests
CMD ["java", "-jar", "target/*.jar"]
