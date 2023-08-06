FROM openjdk:11
LABEL authors="quk"
EXPOSE 8001

#ADD target/chatgpt-api.jar app3.jar
#RUN /bin/sh -c 'touch /app3.jar'
COPY target/*.jar app3.jar
#RUN echo ${PWD} && ls -lR
ENTRYPOINT ["java", "-Xmx256m", "-Xss32m", "-Djava.security.egd=file:/dev/./urandom","-jar","/app3.jar"]