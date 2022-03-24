## Stage 1 : build with maven builder image with native capabilities
FROM quay.io/quarkus/centos-quarkus-maven:22.0.0-java11 AS build
WORKDIR /project
ADD pom.xml /project
RUN mkdir src && mvn -f /project/pom.xml -B de.qaware.maven:go-offline-maven-plugin:1.2.5:resolve-dependencies
COPY src /project/src
USER root
RUN chown -R quarkus /project
USER quarkus
RUN mvn clean package -DskipTests
WORKDIR /project/target/quarkus-app
RUN mv quarkus-run.jar app.jar \
	&& tar -czvf app.tar.gz . \
	&& mv app.tar.gz ../../app.tar.gz

## Stage 2 : running container
FROM registry.access.redhat.com/ubi8/ubi-minimal:8.5-240

ARG JAVA_PACKAGE=java-11-openjdk-headless
ARG RUN_JAVA_VERSION=1.3.8
ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en'
# Install java and the run-java script
# Also set up permissions for user `1001`
RUN microdnf install --nodocs tar gzip curl ca-certificates ${JAVA_PACKAGE} \
    && microdnf update \
    && microdnf clean all \
    && mkdir /deployments \
    && chown 1001 /deployments \
    && chmod "g+rwX" /deployments \
    && chown 1001:root /deployments \
    && curl https://repo1.maven.org/maven2/io/fabric8/run-java-sh/${RUN_JAVA_VERSION}/run-java-sh-${RUN_JAVA_VERSION}-sh.sh -o /deployments/run-java.sh \
    && chown 1001 /deployments/run-java.sh \
    && chmod 540 /deployments/run-java.sh \
    && echo "securerandom.source=file:/dev/urandom" >> /etc/alternatives/jre/lib/security/java.security

# Configure the JAVA_OPTIONS, you can add -XshowSettings:vm to also display the heap size.
ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
COPY --from=build --chown=1001:root /project/app.tar.gz /deployments/app.tar.gz
WORKDIR /deployments
RUN tar -xzvf app.tar.gz && rm -f app.tar.gz

EXPOSE 8080
USER 1001
WORKDIR /

ENTRYPOINT [ "/deployments/run-java.sh" ]
