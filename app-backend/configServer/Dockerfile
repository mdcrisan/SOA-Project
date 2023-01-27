FROM openjdk:17
EXPOSE 8091
ADD target/configServer-0.0.1-SNAPSHOT.jar configServer.jar
ENTRYPOINT ["java", "jar", "configServer.jar"]