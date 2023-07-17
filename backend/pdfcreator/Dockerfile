
FROM maven:3-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:17
WORKDIR /app
COPY --from=build /app/target/pdfcreator.jar .
EXPOSE 8084:8084
CMD ["java", "-jar", "pdfcreator.jar"]