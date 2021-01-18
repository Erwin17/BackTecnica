FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /app
EXPOSE 80
COPY /target/BackTecnica.jar .
ENTRYPOINT ["java", "-jar", "BackTecnica.jar"]