package com.ming.docker.mock.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author CJ
 */
@Component
public class OneBean {

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() {
        for(Map.Entry<String,String>  entry:System.getenv().entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        System.out.println(System.getenv("target.name"));
        System.out.println(environment.getProperty("target.name", "default value"));
        System.out.println(environment.getProperty("BIG", "default big value"));
        System.out.println(environment.getProperty("BIG.NAME", "default big name's value"));
    }
}
