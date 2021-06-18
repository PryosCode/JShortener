FROM openjdk:11-jdk-slim-buster
EXPOSE 80

WORKDIR /gradle
RUN apt-get update && \
    apt-get -y install git wget unzip && \
    git clone https://github.com/PryosCode/JShortener.git . && \
    wget https://services.gradle.org/distributions/gradle-7.1-bin.zip && \
    unzip gradle-7.1-bin.zip && \
    chmod +x gradle-7.1/bin/gradle && \
    ./gradle-7.1/bin/gradle shadowJar

WORKDIR /jshortener
ENTRYPOINT [ "java", "-jar", "/gradle/build/libs/JShortener.jar" ]