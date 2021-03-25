FROM openjdk:11-jdk-slim-buster
EXPOSE 80

RUN apt-get update
RUN apt-get -y install git

WORKDIR /gradle
RUN git clone https://github.com/PryosCode/JShortener.git .
RUN chmod +x gradlew
RUN ./gradlew shadowJar

WORKDIR /jshortener
ENTRYPOINT java -jar /gradle/build/libs/JShortener.jar