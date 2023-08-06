FROM openjdk:11
LABEL authors="quk"
EXPOSE 8761

#ADD target/eureka-server-0.0.1-SNAPSHOT.jar app2.jar
#RUN /bin/sh -c 'touch /app2.jar'
COPY target/*.jar app2.jar
ENTRYPOINT ["java", "-Xmx256m", "-Xss32m", "-Djava.security.egd=file:/dev/./urandom","-jar","/app2.jar"]
#RUN echo ${PWD} && ls -lR