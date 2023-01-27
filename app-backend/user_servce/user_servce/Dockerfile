FROM openjdk:17
EXPOSE 9002
ADD target/user_servce-0.0.1-SNAPSHOT.jar user_servce.jar
ENTRYPOINT ["java", "jar", "user_servce.jar"]