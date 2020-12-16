FROM openjdk:8-jre

WORKDIR /root/
RUN wget https://github.com/PryosCode/JShortener/releases/download/v1.0/JShortener.jar
EXPOSE 80
ENTRYPOINT [ "java", "-jar", "JShortener.jar" ]