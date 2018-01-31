#单app的tomcat Image
两个构建参数(_新版本没了_)
- version 默认 8.0
- versionSuffix 默认 -jre8

#启动事项
* /deploy/context.xml 设定为localhost host的默认context
* /deploy/*.war 都将被复制到 webapps 目录

运行时
-v /my_context.xml:/usr/local/tomcat/conf/Catalina/localhost/context.xml.default
#JDBC
驱动版本
## mysql 6.0.5
## ms sql server 6.1.0
## h2 1.4.192