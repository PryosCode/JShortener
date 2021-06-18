FROM openjdk:11-jdk-slim-buster
EXPOSE 80

WORKDIR /gradle
RUN apt-get update
RUN apt-get -y install git curl
RUN git clone https://github.com/PryosCode/JShortener.git .
RUN curl https://services.gradle.org/distributions/gradle-7.1-bin.zip -o gradle.zip
RUN tar -xvf gradle.zip
RUN ls
RUN chmod +x gradle-7.1/bin/gradle
RUN ./gradle-7.1/bin/gradle shadowJar

WORKDIR /jshortener
ENTRYPOINT [ "java", "-jar", "/gradle/build/libs/JShortener.jar" ]