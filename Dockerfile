FROM openjdk:12
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Historical.jar
ENTRYPOINT ["java","-jar","/Historical.jar"]