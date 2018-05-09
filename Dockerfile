FROM openjdk:8-jre
MAINTAINER Alonso Isidoro <alonsoir@gmail.com>
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/aironman/demo-quartz.jar"]
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/aironman/demo-quartz.jar


# FROM java:8

# ENTRYPOINT ["/usr/bin/java", "-cp", "/opt/app/*:/opt/app/lib/*", "com.github.cstroe.example.Main"]
# EXPOSE 8080

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
# ADD target/dependency           /opt/app/lib

# Add the service itself
# ADD target/app.jar /opt/app/app.jar
