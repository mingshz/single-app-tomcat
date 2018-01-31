package com.ming.docker.tomcat;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * @author CJ
 */
public class Host {
    private final String name;
    private final Set<Resource> resourceSet = new HashSet<>();

    private Host(String name) {
        this.name = name;
    }

    public static Host forName(String name) {
        return new Host(name);
    }

    /**
     * 添加一个资源
     *
     * @param resource 资源
     */
    public void addResource(Resource resource) {
        resourceSet.add(resource);
    }

    public void writeOut(Environment environment) throws XMLStreamException, FileNotFoundException {
        File file = new File(environment.get("CATALINA_HOME")
                + "/conf/Catalina/" + name + "/context.xml.default");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(new FileOutputStream(file)
                , "UTF-8");
        try {
            writer.writeStartDocument();
            writer.writeStartElement("Context");

            resourceSet.forEach(resource -> {
                try {
                    writer.writeStartElement("Resource");
                    writer.writeAttribute("name",resource.getName());
                    writer.writeAttribute("type",resource.getType());
                    resource.writeAttributesTo(writer);

                    writer.writeEndElement();
                } catch (XMLStreamException e) {
                    throw new IllegalStateException(e);
                }

            });

            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
        } finally {
            writer.close();
        }

    }
}
