FROM openjdk:17
COPY target/fluxtesting-0.0.1-SNAPSHOT.jar fluxtesting.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/fluxtesting.jar"]
