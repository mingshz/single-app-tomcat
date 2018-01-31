package com.ming.docker.tomcat;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author CJ
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataSource extends Resource {
    private String maxWait;
    private String maxActive;
    private String maxIdle;
    private String password;
    private String username;
    private String validationQuery;
    private String url;
    private String driverClassName;

    @Override
    public void extendMore(Environment environment, String prefix) {
        if (environment.get(prefix + "MAX_WAIT") != null)
            setMaxWait(environment.get(prefix + "MAX_WAIT"));
        // TODO
    }

    @Override
    public String getType() {
        return "javax.sql.DataSource";
    }

    @Override
    public void writeAttributesTo(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeAttribute("auth", "Container");
        if (maxWait != null)
            writer.writeAttribute("maxWait", maxWait);
        if (maxActive != null)
            writer.writeAttribute("maxActive", maxActive);
        if (maxIdle != null)
            writer.writeAttribute("maxIdle", maxIdle);
        if (password != null)
            writer.writeAttribute("password", password);
        if (username != null)
            writer.writeAttribute("username", username);
        if (validationQuery != null)
            writer.writeAttribute("validationQuery", validationQuery);
        if (url != null)
            writer.writeAttribute("url", url);
        if (driverClassName != null)
            writer.writeAttribute("driverClassName", driverClassName);
    }
}
