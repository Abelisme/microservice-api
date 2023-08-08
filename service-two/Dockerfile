FROM openjdk:11
LABEL authors="quk"
EXPOSE 8002

COPY target/*.jar app4.jar
ENTRYPOINT ["java", "-Xmx256m", "-Xss32m", "-Djava.security.egd=file:/dev/./urandom","-jar","/app4.jar"]