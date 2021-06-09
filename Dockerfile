FROM openjdk:11-jdk-slim-buster
ENV GRADLE_VERSION=7.0.0
EXPOSE 80

WORKDIR /gradle
RUN apt-get update && \
    apt-get -y install git zip unzip && \
    git clone https://github.com/PryosCode/JShortener.git . && \
    curl -s https://get.sdkman.io | bash && \
    source $HOME/.sdkman/bin/sdkman-init.sh && \
    sdk install gradle && \
    gradle shadowJar

WORKDIR /jshortener
ENTRYPOINT [ "java", "-jar", "/gradle/build/libs/JShortener.jar" ]