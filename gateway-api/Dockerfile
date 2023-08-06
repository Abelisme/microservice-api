#FROM mavne:3.6.3-openjdk-11-slim AS MAVEN_TOOL_CHAIN
#COPY pom.xml /tmp
#COPY src /tmp/src
#WORKDIR /tmp
#RUN mvn clean package

FROM openjdk:11
LABEL authors="quk"
EXPOSE 8080

#ADD target/chatgpt-api.jar app.jar
#RUN /bin/sh -c 'touch /app.jar'
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-Xmx256m", "-Xss32m", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#RUN echo ${PWD} && ls -lR