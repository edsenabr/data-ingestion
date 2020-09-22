FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-slim
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY kafka.client.truststore.jks /opt/kafka.client.truststore.jks
COPY target/*.jar /opt/data-ingestion.jar
ENTRYPOINT exec java $JAVA_OPTS -jar data-ingestion.jar