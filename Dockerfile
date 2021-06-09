FROM openjdk:11-jdk-slim-buster
EXPOSE 80

WORKDIR /gradle
RUN apt-get update && \
    apt-get -y install git zip unzip && \
    git clone https://github.com/PryosCode/JShortener.git . && \
    GRADLE_VERSION=7.0.1 && \
    wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp && \
    sudo unzip -d /opt/gradle /tmp/gradle-${GRADLE_VERSION}-bin.zip && \
    GRADLE_HOME=/opt/gradle/latest && \
    PATH=${GRADLE_HOME}/bin:${PATH} && \
    gradle shadowJar

WORKDIR /jshortener
ENTRYPOINT [ "java", "-jar", "/gradle/build/libs/JShortener.jar" ]