## syntax=docker/dockerfile:1

FROM openjdk:18
ADD ./target/mybank-image-new.jar mybank.jar
ENTRYPOINT ["java", "-jar", "mybank.jar"]
EXPOSE 8080
#
#FROM eclipse-temurin:11-jdk-jammy as base
#WORKDIR /app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:resolve
#COPY src ./src
#
#FROM base as development
#CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]
#
#FROM base as build
#RUN ./mvnw package
#
#FROM eclipse-temurin:11-jre-jammy as production
#EXPOSE 8080
#COPY --from=build /app/target/mybank-image-new-*.jar /mybank-image-new.jar
#CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/mybank-image-new.jar"]