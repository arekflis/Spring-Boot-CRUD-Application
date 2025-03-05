FROM eclipse-temurin:22.0.2_9-jdk

LABEL org.opencontainers.image.title="player"
LABEL org.opencontainers.image.description="Player Microservice"
LABEL org.opencontainers.image.author="arekflis"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"

LABEL build_version=""
LABEL maintainer=""

ENV SERVER_PORT=8080
ENV VERSION="0.0.1-SNAPSHOT"

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:player
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=password

ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

ENV SPRING_H2_CONSOLE_ENABLED=true

EXPOSE 8080

COPY target/playerMicroservice-${VERSION}.jar /opt/player/player-app.jar

CMD ["java", "-jar", "/opt/player/player-app.jar"]