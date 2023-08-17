FROM eclipse-temurin:20-alpine
VOLUME /tmp
COPY target/*.jar animedb.jar
ENTRYPOINT [ "java", "-jar", "/animedb.jar" ]
EXPOSE 8080