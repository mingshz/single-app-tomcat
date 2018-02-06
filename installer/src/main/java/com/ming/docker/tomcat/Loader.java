package com.ming.docker.tomcat;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 可支持Tomcat Resource也可支持普通Property
 * --!mysqlDataSource-jdbc/dating username:password@host:port[|maxWait:300]
 * --!activemqConnectionFactory-jms/ConnectionFactory factoryClass[|brokerName=LocalActiveMQBroker]
 * --!activemqTopic-jms/one factoryClass[|physicalName=one]
 * --normal value
 * 如果按这个方式部署会存在暴露以及难以维护的问题
 * 1，single-app-tomcat 应该提供一种易于维护的部署描述文件
 * 2，生产环境应该自行构建image
 *
 * @author CJ
 */
public class Loader {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

//        System.out.println("length:" + args.length);
//        for (String a : args) {
//            System.out.println("arg:" + a);
//        }
//        new Loader()
//                .start(new SystemEnvironment());
        new Loader().start(new SystemEnvironment());

        System.getenv().forEach((k,v)->System.out.println(k+":"+v));
        System.getProperties().list(System.out);

        Process process;
        if (System.getenv("JPDA_ENABLE") != null) {
            System.out.println("Loader is loading Tomcat(debug) Catalina instance...");
            process = new ProcessBuilder().inheritIO()
                    .command("catalina.sh", "jpda", "run").start();
        } else {
            System.out.println("Loader is loading Tomcat Catalina instance...");
            process = new ProcessBuilder().inheritIO()
                    .command("catalina.sh", "run").start();
        }


        System.exit(process.waitFor());
    }

    void start(Environment environment) throws IOException, URISyntaxException {
        File file = new File(environment.get("CATALINA_HOME")
                + "/conf/Catalina/" + "localhost" + "/context.xml.default");

        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs())
                throw new IllegalStateException("failed to create folder:" + file.getParentFile());
        }
        Path home = Paths.get("/deploy");

        Path contextPath = home.resolve("context.xml");
        if (Files.exists(contextPath)) {
            if (!Files.exists(file.toPath()))
                Files.move(contextPath, file.toPath());
        }
        // 其他  war
        Path appHome = Paths.get(environment.get("CATALINA_HOME")).resolve("webapps");
        if (Files.exists(home))
            Files.list(home)
                    .filter(path -> path.getFileName().toString().endsWith(".war"))
                    .forEach(path -> {
                        // 取出名字
                        String name = path.getFileName().toString();
                        Path target = appHome.resolve(name);
                        try {
                            if (!Files.exists(target))
                                Files.move(path, target);
                        } catch (IOException e) {
                            throw new IllegalStateException(e);
                        }
                    });
    }

    void start2(Environment environment) throws FileNotFoundException, XMLStreamException {
//        MysqlDataSource dataSource = new MysqlDataSource(
//                environment.get("DS_HOST")
//                , Integer.parseInt(environment.get("DS_PORT"))
//                , environment.get("DS_NAME")
//                , environment.get("DS_USERNAME")
//                , environment.get("DS_PASSWORD")
//        );
//        dataSource.setName("jdbc/dating");
//
//        dataSource.extendMore(environment, "DS_");
//        final Host localhost = Host.forName("localhost");
//        localhost.addResource(dataSource);
//        localhost.writeOut(environment);
    }
}
