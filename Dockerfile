FROM amazoncorretto:8

# copy the packaged jar file into docker image
COPY target/demo-0.0.1-SNAPSHOT.jar /demo.jar

COPY src/main/resources/application.properties /application.properties

WORKDIR /

# set the startup command to execute jar file
CMD ["java", "-jar", "/demo.jar", "--spring.config.location=application.properties"]
