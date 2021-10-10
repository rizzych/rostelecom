#the first stage of our build will use a maven 3.8.1 parent image
FROM maven:3.8.1-amazoncorretto-8

# copy the pom and src code to the container
COPY ./ ./

# package our application code
RUN mvn clean package -DskipTests

COPY src/main/resources/application.properties /application.properties

# set the startup command to execute jar file
CMD ["java", "-jar", "./target/demo-0.0.1-SNAPSHOT.jar", "--spring.config.location=application.properties"]
