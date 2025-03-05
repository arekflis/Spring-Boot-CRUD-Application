FROM eclipse-temurin:22.0.2_9-jdk

LABEL org.opencontainers.image.title="gateway"
LABEL org.opencontainers.image.description="Gateway Microservice"
LABEL org.opencontainers.image.authors="arekflis"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"

LABEL build_version=""
LABEL maintainer=""

ENV SERVER_PORT=8080
ENV VERSION="0.0.1-SNAPSHOT"

ENV PLAYERS_MICROSERVICE_URL=http://localhost:8081
ENV CLUBS_MICROSERVICE_URL=http://localhost:8080
ENV GATEWAY_MICROSERVICE_URL=localhost:8082

EXPOSE 8080

COPY target/gatewayMicroservice-${VERSION}.jar /opt/gateway/gateway-app.jar

CMD ["java", "-jar", "/opt/gateway/gateway-app.jar"]