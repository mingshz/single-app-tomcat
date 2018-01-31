package com.ming.docker.tomcat;

/**
 * @author CJ
 */
public class MysqlDataSource extends DataSource {
    public MysqlDataSource(String host, int port, String databaseName, String username, String password) {
        setDriverClassName("com.mysql.jdbc.Driver");
        setUrl(String.format("jdbc:mysql://%s:%d/%s?useUnicode=true&characterEncoding=utf8&useServerPrepStmts=false&autoReconnect=true", host, port, databaseName));
        setUsername(username);
        setPassword(password);
    }
}
