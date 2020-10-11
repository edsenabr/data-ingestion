FROM amazoncorretto:8-alpine-jre
WORKDIR /opt
COPY kafka.client.truststore.jks /opt/kafka.client.truststore.jks
COPY target/*.jar /opt/data-ingestion.jar
ENTRYPOINT exec java $JAVA_OPTS -jar data-ingestion.jar --spring.profiles.active=aws --spring.config.location=/tmp/