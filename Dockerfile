FROM adoptopenjdk/openjdk11:jre-11.0.8_10-alpine
WORKDIR /opt
COPY kafka.client.truststore.jks /opt/kafka.client.truststore.jks
COPY target/*.jar /opt/data-ingestion.jar
ENTRYPOINT exec java $JAVA_OPTS -jar data-ingestion.jar --spring.profiles.active=aws --spring.config.location=/tmp/