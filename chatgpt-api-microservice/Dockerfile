FROM openjdk:11
LABEL authors="quk"
EXPOSE 8081

#ADD target/chatgpt-api.jar app3.jar
#RUN /bin/sh -c 'touch /app3.jar'
COPY target/*.jar backend-api.jar
#RUN echo ${PWD} && ls -lR
ENTRYPOINT ["java", "-Xmx256m", "-Xss32m", "-Djava.security.egd=file:/dev/./urandom","-jar","/backend-api.jar"]