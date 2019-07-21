FROM adoptopenjdk/openjdk12:latest
MAINTAINER Alonso Isidoro <alonsoir@gmail.com>
ARG JAR_FILE
RUN mkdir /opt/app
COPY target/demo-quartz-0.0.2-SNAPSHOT.jar /opt/app/demo-quartz.jar
CMD ["java", "-jar", "/opt/app/demo-quartz.jar"]