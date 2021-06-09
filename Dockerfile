FROM openjdk:11-jdk-slim-buster
EXPOSE 80

WORKDIR /gradle
RUN apt-get update && \
    apt-get -y install git unzip && \
    git clone https://github.com/PryosCode/JShortener.git . && \
    GRADLE_VERSION=7.0.1 && \
    curl https://services.gradle.org/distributions/gradle-7.0.1-bin.zip -o gradle.zip && \
    unzip gradle.zip && \
    chmod +x gradle-7.0.1/bin/gradle && \
    ./gradle-7.0.1/bin/gradle shadowJar

WORKDIR /jshortener
ENTRYPOINT [ "java", "-jar", "/gradle/build/libs/JShortener.jar" ]