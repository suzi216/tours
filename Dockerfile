FROM maven:3.9.6-amazoncorretto-21 AS build
COPY . .
RUN mvn  clean package -DskipTests

# Package stage
FROM eclipse-temurin:21-jre-jammy
COPY --from=build /target/*.jar tour-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/tour-service.jar"]

