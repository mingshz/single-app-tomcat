package com.ming.docker.tomcat;

import lombok.Data;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author CJ
 */
@Data
public abstract class Resource {
    private String name;

    /**
     * 填充更多属性，从环境中获取
     *
     * @param environment
     * @param prefix      环境前置字符串
     */
    public abstract void extendMore(Environment environment, String prefix);

    /**
     * @return 资源类型
     */
    public abstract String getType();

    /**
     * 将属性写入xml文件
     *
     * @param writer xml写入者
     */
    public abstract void writeAttributesTo(XMLStreamWriter writer) throws XMLStreamException;
}
