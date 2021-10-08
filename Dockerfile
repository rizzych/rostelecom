#the first stage of our build will use a maven 3.8.1 parent image
FROM maven:3.8.1-amazoncorretto-8 AS MAVEN_BUILD

# copy the pom and src code to the container
COPY ./ ./

# package our application code
RUN mvn clean package

FROM amazoncorretto:8

# copy the packaged jar file into docker image
COPY target/demo-0.0.1-SNAPSHOT.jar /demo.jar

COPY src/main/resources/application.properties /application.properties

WORKDIR /

# set the startup command to execute jar file
CMD ["java", "-jar", "/demo.jar", "--spring.config.location=application.properties"]
