FROM maven:3-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
RUN mvn wrapper:wrapper

FROM amazoncorretto:17
WORKDIR /app
COPY --from=build /app/target/converter.jar .
EXPOSE 8082
CMD ["java", "-jar", "converter.jar"]