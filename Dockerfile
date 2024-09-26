FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/store_user-0.0.1.jar
COPY ${JAR_FILE} user.jar
ENTRYPOINT ["java","-jar","user.jar", "-Dspring.config.location=classpath:application.properties"]