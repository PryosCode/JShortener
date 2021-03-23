FROM openjdk:11-jre-slim
WORKDIR /jshortener
EXPOSE 80

RUN apt-get update
RUN apt-get -y install git

RUN git clone https://github.com/PryosCode/JShortener.git .
RUN chmod +x gradlew
RUN ./gradlew shadowJar

ENTRYPOINT java -jar JShortener.jar