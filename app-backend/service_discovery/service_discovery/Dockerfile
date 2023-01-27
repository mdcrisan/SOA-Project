FROM openjdk:17
EXPOSE 8761
ADD target/service_discovery-0.0.1-SNAPSHOT.jar service_discovery.jar
ENTRYPOINT ["java", "jar", "service_discovery.jar"]