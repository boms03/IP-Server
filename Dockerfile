FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} ip_server.jar
ENTRYPOINT ["java","-jar","ip_server.jar", "--spring.profiles.active=dev"]