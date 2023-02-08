FROM openjdk:17-jdk-slim
VOLUME /tmp
EXPOSE 8080
ADD target/rabbit3-0.0.1-SNAPSHOT.jar rabbit3.jar 
ENTRYPOINT ["java","-jar","/rabbit3.jar"]