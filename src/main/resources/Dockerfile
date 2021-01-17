FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /app
EXPOSE 80
COPY /target/TecnicaRest-Backend.jar .
ENTRYPOINT ["java", "-jar", "TecnicaRest-Backend.jar"]