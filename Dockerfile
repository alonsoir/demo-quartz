FROM adoptopenjdk/openjdk12:latest
MAINTAINER Alonso Isidoro <alonsoir@gmail.com>
ARG JAR_FILE
ARG crypt_type
RUN mkdir /opt/app
COPY target/demo-quartz-0.0.2-SNAPSHOT.jar /opt/app/demo-quartz.jar
COPY entry-point.sh /
RUN chmod +x entry-point.sh
ENTRYPOINT ["/entry-point.sh"]
CMD ["${crypt_type}"]
