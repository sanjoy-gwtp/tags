FROM maven:3.8.1-jdk-8

RUN mkdir /tmp/tags

COPY . /tmp/tags

WORKDIR /tmp/tags

RUN mvn clean package

COPY ./target/tags-0.0.1-SNAPSHOT.jar /opt/app.jar

EXPOSE 8080 5005

ENV JAVA_OPTS=""

WORKDIR /opt

CMD java ${XX_JAVA_OPTS} ${JAVA_OPTS} -Dspring.profiles.active=$CONFIG_PROFILES -Dspring.cloud.config.uri=$CONFIG_SERVER_URI -jar app.jar --spring.profiles.active=${CONFIG_PROFILES} --spring.cloud.config.uri=${CONFIG_SERVER_URI}
