package com.ming.docker.tomcat;

import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author CJ
 */
public class InstallerTest {

    @Test
    public void start() throws IOException, XMLStreamException, URISyntaxException {
        MockEnvironment environment = new MockEnvironment();
        environment.put("CATALINA_HOME", new File("./target/tomcat").getAbsolutePath());
        environment.put("DS_PORT", "3306");

        new Loader().start(environment);
    }
}