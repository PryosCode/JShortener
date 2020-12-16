FROM openjdk:8-jre

WORKDIR /root/
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "JShortener.jar" ]
CMD [ "" ]