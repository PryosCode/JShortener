FROM openjdk:11-jdk-slim-buster
ENV GRADLE_VERSION=7.0.0
EXPOSE 80

WORKDIR /gradle
RUN apt-get update && \
    apt-get -y install git && \
    git clone https://github.com/PryosCode/JShortener.git . && \
    wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp && \
    sudo unzip -d /opt/gradle /tmp/gradle-${GRADLE_VERSION}-bin.zip && \
    sudo ln -s /opt/gradle/gradle-${GRADLE_VERSION} /opt/gradle/latest && \
    export GRADLE_HOME=/opt/gradle/latest && \
    export PATH=${GRADLE_HOME}/bin:${PATH} && \
    gradle shadowJar

WORKDIR /jshortener
ENTRYPOINT [ "java", "-jar", "/gradle/build/libs/JShortener.jar" ]