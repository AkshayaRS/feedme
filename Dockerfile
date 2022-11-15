FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/feedme-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} feedme.jar
ENTRYPOINT ["java","-jar","/feedme.jar"]