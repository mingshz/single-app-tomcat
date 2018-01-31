#ARG version=8.0
#ARG versionSuffix=-jre8
#FROM tomcat:${version}${versionSuffix}
FROM tomcat:8.0-jre8

#删除原来的APP
RUN rm -rf ${CATALINA_HOME}/webapps/*

#复制driver
COPY ./drivers/h2-1.4.192.jar ${CATALINA_HOME}/lib/
COPY ./drivers/mssql-jdbc-6.1.0.jre8.jar ${CATALINA_HOME}/lib/
COPY ./drivers/mysql-connector-java-6.0.5.jar ${CATALINA_HOME}/lib/

COPY maven /loader
#COPY ../../../../target/installer-*-jar-with-dependencies.jar /loader.jar
#COPY ./mock/target/mock-*-jar-with-dependencies.jar /mock.jar
#ENTRYPOINT java -jar /mock.jar
RUN curl https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh > /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

#VOLUME ["/deploy/context.xml"]
#VOLUME ["/newDD"]

ENTRYPOINT java -cp /loader/installer.jar com.ming.docker.tomcat.Loader
