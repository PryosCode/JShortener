FROM openjdk:11-jdk-slim-buster
EXPOSE 80

RUN apt-get update && \
    apt-get -y install git

WORKDIR /gradle
RUN git clone https://github.com/PryosCode/JShortener.git . && \
    chmod +x gradlew && \
    ./gradlew shadowJar

WORKDIR /jshortener
ENTRYPOINT [ "java", "-jar", "/gradle/build/libs/JShortener.jar" ]