#独立编译

FROM maven:3.5.2-jdk-8 as builder
ADD . /build/
WORKDIR /build/
RUN ["mvn","package"]

#ARG version=8.0
#ARG versionSuffix=-jre8
#FROM tomcat:${version}${versionSuffix}
FROM tomcat:8.0-jre8

#删除原来的APP
RUN rm -rf ${CATALINA_HOME}/webapps/*

#复制driver
COPY installer/src/main/docker/drivers/h2-1.4.192.jar ${CATALINA_HOME}/lib/
COPY installer/src/main/docker/drivers/mssql-jdbc-6.1.0.jre8.jar ${CATALINA_HOME}/lib/
COPY installer/src/main/docker/drivers/mysql-connector-java-6.0.5.jar ${CATALINA_HOME}/lib/

#部署builder构建完成的 jar包
#COPY maven /loader
COPY --from=builder /build/installer/target/installer-jar-with-dependencies.jar /loader/

#COPY ../../../../target/installer-*-jar-with-dependencies.jar /loader.jar
#COPY ./mock/target/mock-*-jar-with-dependencies.jar /mock.jar
#ENTRYPOINT java -jar /mock.jar
RUN curl https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh > /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

#VOLUME ["/deploy/context.xml"]
#VOLUME ["/newDD"]

#ENTRYPOINT java -cp /loader/installer.jar com.ming.docker.tomcat.Loader
ENTRYPOINT java -jar /loader/installer-jar-with-dependencies.jar
